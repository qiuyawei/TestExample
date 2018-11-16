package com.wm.example.test.exampletest.observable;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：观察者
 */
public interface Observer<T> {
    public void onUpdate(Observable<T> observable, T data);
}
