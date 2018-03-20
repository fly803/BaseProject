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

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

abstract class AbstractSubscriber<T> implements Consumer<T>, Disposable {

    private volatile boolean disposed;

    @Override
    public void accept(T event) {
        try {
            acceptEvent(event);
        } catch (Exception e) {
            throw new RuntimeException("Could not dispatch event: " + event.getClass(), e);
        }
    }

    @Override
    public void dispose() {
        if (!disposed) {
            disposed = true;
            release();
        }
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    protected abstract void acceptEvent(T event) throws Exception;

    protected abstract void release();

}
