package com.lec.observer;

/** 抽象观察者角色:
 * 为所有具体的观察者定义一个接口,在得到主题的通知时更新自己.
 * @author zhwanwan
 * @create 2019-07-03 11:08 PM
 */
public interface Watcher {

    void update(String str);

}
