package com.lec.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色:
 * 在具体主题内部状态改变时,给所有登记过的观察者发出通知.具体主题角色通常用一个子类实现.
 *
 * @author zhwanwan
 * @create 2019-07-03 11:12 PM
 */
public class ConcreteWatched implements Watched {

    private List<Watcher> watchers = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {

        watchers.forEach(w -> w.update(str));

    }
}
