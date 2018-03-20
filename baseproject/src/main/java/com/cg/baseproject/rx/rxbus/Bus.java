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

import io.reactivex.functions.Consumer;

public interface Bus {

    void register(@NonNull Object observer);

    <T> CustomSubscriber<T> obtainSubscriber(@NonNull Class<T> eventClass, @NonNull Consumer<T> receiver);

    <T> void registerSubscriber(@NonNull Object observer, @NonNull CustomSubscriber<T> subscriber);

    void unregister(@NonNull Object observer);

    void post(@NonNull Object event);

}
