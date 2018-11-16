package com.wm.example.test.lib;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：
 */
public interface Observal<T> {
    public void onUpdate(ObservalManage<T> observalManage,T data);
}
