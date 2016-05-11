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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProxyTest {
    @Test
    public void implementsSubject() throws Exception {
        MyProxy proxy = new MyProxy(null);

        assertThat(proxy, instanceOf(Subject.class));
    }

    @Test(expected = ControlledExecution.class)
    public void controlsTheActionOnSubject() throws Exception {
        MyProxy proxy = buildProxy();

        proxy.controlledAction();
    }

    @Test(expected = DelegatedOnSubject.class)
    public void canDelegateToProxiedClass() throws Exception {
        MyProxy proxy = buildProxy();

        proxy.delegatedAction();
    }

    @Test(expected = ConditionedExecution.class)
    public void canDoConditionalExecution() throws Exception {
        MyProxy proxy = buildProxy();

        proxy.conditionedExecution(true);
    }

    @Test(expected = DelegatedOnSubject.class)
    public void delegatesToProxiedClassWhenNotConditioned() throws Exception {
        MyProxy proxy = buildProxy();

        proxy.conditionedExecution(false);
    }

    @Test(expected = DelegatedOnSubject.class)
    public void canInitializeTheSubjectJustInTime() throws Exception {
        MyProxy proxy = new MyProxy();

        proxy.delegatedAction();
    }

    private MyProxy buildProxy() {
        RealSubject real = new RealSubject();
        return new MyProxy(real);
    }
}
