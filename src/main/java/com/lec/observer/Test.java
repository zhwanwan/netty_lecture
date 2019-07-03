package com.lec.observer;

/**
 * @author zhwanwan
 * @create 2019-07-03 11:17 PM
 */
public class Test {
    public static void main(String[] args) {
        Watched girl = new ConcreteWatched();//主题

        Watcher watcher1 = new ConcreteWatcher(); //观察者1
        Watcher watcher2 = new ConcreteWatcher(); //观察者2
        Watcher watcher3 = new ConcreteWatcher(); //观察者3

        girl.addWatcher(watcher1);
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);

        girl.notifyWatchers("大家好!");

        girl.removeWatcher(watcher2);

        girl.notifyWatchers("你们好!");
    }
}
