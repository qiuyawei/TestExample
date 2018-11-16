package com.wm.example.test.exampletest.utils;

import java.util.Random;

/**
 * Created by Author:qyw
 * on 2018/10/24.
 * QQ:448739075
 * 描述：
 */
public class TestA {
    private CallBack callBack;
    public TestA(CallBack callBack){
        this.callBack=callBack;
    }

    public void getRandom(){
        int random=new Random().nextInt(9);
//        callBack.getValue(random);
    }
}
