package com.lec.decorator;

/**
 * @author zhwanwan
 * @create 2019-06-26 10:44 AM
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
