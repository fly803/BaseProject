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

class AnnotatedSubscriber<T> extends AbstractSubscriber<T> {

    private final int hashCode;

    private Object observer;
    private Method method;

    AnnotatedSubscriber(@NonNull Object observer, @NonNull Method method) {
        this.observer = observer;
        this.method = method;

        hashCode = 31 * observer.hashCode() + method.hashCode();
    }

    @Override
    protected void acceptEvent(T event) throws Exception {
        method.invoke(observer, event);
    }

    @Override
    protected void release() {
        observer = null;
        method = null;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        AnnotatedSubscriber<?> that = (AnnotatedSubscriber<?>) other;

        return observer.equals(that.observer) && method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

}
