/*
 * Copyright (C) 2016 Pablo Guardiola Sánchez.
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

package com.pguardiola.designpatterns.proxy;

public class MyProxy implements Subject {

    private RealSubject real;

    public MyProxy() {
    }

    public MyProxy(RealSubject subject) {
        this.real = subject;
    }

    public void controlledAction() {
        throw new ControlledExecution();
    }

    public void delegatedAction() {
        obtainSubject().delegatedAction();
    }

    public void conditionedExecution(boolean isConditioned) {
        if (isConditioned) throw new ConditionedExecution();

        obtainSubject().delegatedAction();
    }

    private RealSubject obtainSubject() {
        if (null == this.real) return new RealSubject();

        return this.real;
    }
}
