package com.hgz.test.rxbus.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2017/10/18.
 */

public class RxBus {
    private static Subject bus;
    private static RxBus instance;
    public RxBus(){
        bus=new SerializedSubject(PublishSubject.create());
    }
    //单例模式
    public static synchronized RxBus getInstance(){
        if (instance==null){
            instance=new RxBus();
        }
        return instance;
    }
    //发送
    public static void post(Object o){
        if (hasObserver()){
            bus.onNext(o);
        }
    }
    //接收
    public static  <T> Observable<T> toObservable(Class<T> clazz) {

        return bus.ofType(clazz);
    }
    //判断 是否有订阅者
    public static boolean hasObserver() {
        return bus.hasObservers();
    }
}
