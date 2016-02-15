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

package com.pguardiola.designpatterns.decorator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class DecoratorTest {
  @Test public void hasSameInterface() throws Exception {
    Decorator decorator = new ConcreteDecorator(null);

    assertThat(decorator, instanceOf(Component.class));
  }

  @Test public void doesNotChangeBehavior() throws Exception {
    Component concreteComponent = new ConcreteComponent();
    Decorator decorator = new ConcreteDecorator(concreteComponent);

    assertThat(decorator.operation(), is(equalTo(concreteComponent.operation())));
  }

  @Test public void addsBehavior() throws Exception {
    Component concreteComponent = new ConcreteComponent();
    Decorator decorator = new ConcreteDecorator(concreteComponent);

    String result = concreteComponent.decoratedOperation();
    String decorated = decorator.decoratedOperation();
    String decoration = " decorated";

    assertThat(decorated, is(equalTo(result + decoration)));
  }

  @Test public void isStackable() throws Exception {
    Component concreteComponent = new ConcreteComponent();
    Decorator decorator = new ConcreteDecorator(concreteComponent);
    Decorator stackedDecorator = new ConcreteDecorator(decorator);

    String result = concreteComponent.decoratedOperation();
    String stackedDecorated = stackedDecorator.decoratedOperation();
    String decoration = " decorated";
    String stackedDecoration = " decorated";

    assertThat(stackedDecorated, is(equalTo(result + decoration + stackedDecoration)));
  }
}
