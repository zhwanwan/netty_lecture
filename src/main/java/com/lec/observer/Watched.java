package com.lec.observer;

/** 抽象主题角色:
 * 把所有观察者对象的引用保存在一个集合中,每个抽象主题角色都可以有任意数据量的观察者.
 * 抽象主题提供一个接口,可以增加和删除观察者角色.一般用一个抽象类或接口来实现.
 * @author zhwanwan
 * @create 2019-07-03 11:07 PM
 */
public interface Watched {

    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatchers(String str);

}
