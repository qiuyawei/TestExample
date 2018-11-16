package com.wm.example.test.exampletest.observable;

import java.util.ArrayList;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：
 */
public class Observable<T> implements Observer<T> {
    private ArrayList<Observable<T>> arrayList=new ArrayList<>();

    //注册
    public void register(Observable<T> observable){
        if(observable==null){
            throw new NullPointerException("observable==null");
        }else {
            if(!arrayList.contains(observable)){
                arrayList.add(observable);
            }
        }
    }

    //取消注册
    public void unRegister(Observable<T> observable){
        if(observable==null){
            throw new NullPointerException("observable==null");
        }else {
           arrayList.remove(observable);
        }
    }

    public void notifyObsers(T data){
        for(Observable<T> observable:arrayList){
            observable.onUpdate(observable,data);

        }
    }

    @Override
    public void onUpdate(Observable<T> observable, T data) {

    }
}
