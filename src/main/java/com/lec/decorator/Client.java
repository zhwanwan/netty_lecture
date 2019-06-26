package com.lec.decorator;

/**
 * @author zhwanwan
 * @create 2019-06-26 10:47 AM
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();
    }
}
