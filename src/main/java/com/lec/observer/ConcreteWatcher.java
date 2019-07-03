package com.lec.observer;

/**具体观察者角色:
 * 该角色实现抽象观察者角色所要求的更新接口,以便使本身的状态与主题的状态相协调.
 * 如果需要,具体观察者角色可以保存一个指向具体主题角色的引用.通常用一个子类实现.
 * @author zhwanwan
 * @create 2019-07-03 11:16 PM
 */
public class ConcreteWatcher implements Watcher {
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
