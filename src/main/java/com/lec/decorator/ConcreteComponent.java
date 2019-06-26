package com.lec.decorator;

/**
 * @author zhwanwan
 * @create 2019-06-26 10:43 AM
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
