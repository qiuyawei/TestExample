package com.wm.example.test.exampletest.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Author:qyw
 * on 2018/10/25.
 * QQ:448739075
 * 描述：
 */
public class MActivityMannage {
    private Stack<Activity> activities=new Stack<>();
    private static MActivityMannage mActivityMannage;
    //构造方法私有化，单例模式第一步
    private MActivityMannage(){}
    /**
     * 保证线程安全
     * @return
     */
    public static MActivityMannage getInstance(){
        if(mActivityMannage==null){
            synchronized (MActivityMannage.class){
                mActivityMannage=new MActivityMannage();
            }
        }

        return mActivityMannage;
    }

    /**
     * 把activity放入栈
     * @param activity
     */
    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public  void ClearStack(){
        activities.clear();
    }
}
