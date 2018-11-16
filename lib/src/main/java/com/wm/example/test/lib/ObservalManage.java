package com.wm.example.test.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：
 */
public class ObservalManage<T> {
    private List<Observal<T>> observals = new ArrayList<>();

    public void register(Observal<T> observal) {
        if (observal == null) {
            throw new NullPointerException("observal==null");
        } else {
            synchronized (this) {
                if (!observals.contains(observal)) {
                    observals.add(observal);
                }
            }
        }
    }

    public void unRegister(Observal<T> observal) {
        if (observal != null)
            observals.remove(observal);
    }

    public void notifyAll(T data) {
        for (Observal<T> observal : observals) {
            observal.onUpdate(this, data);
        }
    }
}
