package com.wm.example.test.exampletest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.wm.example.test.exampletest.IMyAidlInterface;

/**
 * Created by Author:qyw
 * on 2018/11/15.
 * QQ:448739075
 * 描述：
 */
public class MyService extends Service {
    IMyAidlInterface.Stub myAidlInterface=new MyStub();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myAidlInterface;
    }

    public class MyStub extends IMyAidlInterface.Stub{
        @Override
        public int cacluteSum(double a, double b) throws RemoteException {
            return (int) (a+b);
        }
    }
}
