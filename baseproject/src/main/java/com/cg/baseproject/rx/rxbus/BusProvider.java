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

public final class BusProvider {

    private BusProvider() {
    }

    public static Bus getInstance() {
        return BusHolder.INSTANCE;
    }

    private static final class BusHolder {
        final static Bus INSTANCE = new RxBus();
    }
}
