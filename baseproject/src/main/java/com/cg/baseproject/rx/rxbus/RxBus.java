/*
 * Copyright (C) 2017 Anadea Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cg.baseproject.rx.rxbus;

import android.support.annotation.NonNull;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class RxBus implements Bus {

    private final ConcurrentMap<Class<?>, CompositeDisposable> OBSERVERS
            = new ConcurrentHashMap<>();
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<CustomSubscriber<?>>> SUBSCRIBERS
            = new ConcurrentHashMap<>();

    private final Subject<Object> bus = PublishSubject.create().toSerialized();

    public void register(@NonNull Object observer) {
        ObjectHelper.requireNonNull(observer, "Observer to register must not be null.");

        Class<?> observerClass = observer.getClass();

        if (OBSERVERS.putIfAbsent(observerClass, new CompositeDisposable()) != null){
            return;
        }
//        if (OBSERVERS.putIfAbsent(observerClass, new CompositeDisposable()) != null)
//            throw new IllegalArgumentException("Observer has already been registered.");

        CompositeDisposable composite = OBSERVERS.get(observerClass);

        Set<Class<?>> events = new HashSet<>();

        for (Method method : observerClass.getDeclaredMethods()) {

            if (method.isBridge() || method.isSynthetic())
                continue;

            if (!method.isAnnotationPresent(Subscribe.class))
                continue;

            int mod = method.getModifiers();

            if (Modifier.isStatic(mod) || !Modifier.isPublic(mod))
                throw new IllegalArgumentException("Method " + method.getName() +
                        " has @Subscribe annotation must be public, non-static");

            Class<?>[] params = method.getParameterTypes();

            if (params.length != 1)
                throw new IllegalArgumentException("Method " + method.getName() +
                        " has @Subscribe annotation must require a single argument");

            Class<?> eventClass = params[0];

            if (eventClass.isInterface())
                throw new IllegalArgumentException("Event class must be on a concrete class type.");

            if (!events.add(eventClass))
                throw new IllegalArgumentException("Subscriber for " + eventClass.getSimpleName() +
                        " has already been registered.");

            composite.add(bus.ofType(eventClass)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new AnnotatedSubscriber<>(observer, method)));
        }

    }

    public <T> CustomSubscriber<T> obtainSubscriber(@NonNull Class<T> eventClass,
                                                    @NonNull Consumer<T> receiver) {
        ObjectHelper.requireNonNull(eventClass, "Event class must not be null.");
        if (eventClass.isInterface())
            throw new IllegalArgumentException("Event class must be on a concrete class type.");
        ObjectHelper.requireNonNull(receiver, "Receiver must not be null.");
        return new CustomSubscriber<>(eventClass, receiver);
    }

    public <T> void registerSubscriber(@NonNull Object observer, @NonNull CustomSubscriber<T> subscriber) {
        ObjectHelper.requireNonNull(observer, "Observer to register must not be null.");
        ObjectHelper.requireNonNull(subscriber, "Subscriber to register must not be null.");

        SUBSCRIBERS.putIfAbsent(observer.getClass(), new CopyOnWriteArraySet<CustomSubscriber<?>>());
        Set<CustomSubscriber<?>> subscribers = SUBSCRIBERS.get(observer.getClass());
        if (subscribers.contains(subscriber))
            throw new IllegalArgumentException("Subscriber has already been registered.");
        else
            subscribers.add(subscriber);

        Observable<T> observable = bus.ofType(subscriber.getEventClass())
                .observeOn(subscriber.getScheduler() == null ?
                        AndroidSchedulers.mainThread() : subscriber.getScheduler());

        Class<?> observerClass = observer.getClass();

        OBSERVERS.putIfAbsent(observerClass, new CompositeDisposable());
        CompositeDisposable composite = OBSERVERS.get(observerClass);

        composite.add(((subscriber.getFilter() == null) ? observable :
                observable.filter(subscriber.getFilter()))
                .subscribe(subscriber));
    }

    public void unregister(@NonNull Object observer) {
        ObjectHelper.requireNonNull(observer, "Observer to unregister must not be null.");
        CompositeDisposable composite = OBSERVERS.get(observer.getClass());
        ObjectHelper.requireNonNull(composite, "Missing observer, it was registered?");
        composite.dispose();
        OBSERVERS.remove(observer.getClass());

        Set<CustomSubscriber<?>> subscribers = SUBSCRIBERS.get(observer.getClass());
        if (subscribers != null) {
            subscribers.clear();
            SUBSCRIBERS.remove(observer.getClass());
        }
    }

    public void post(@NonNull Object event) {
        ObjectHelper.requireNonNull(event, "Event must not be null.");
        bus.onNext(event);
    }

}
