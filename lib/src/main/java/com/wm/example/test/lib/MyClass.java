package com.wm.example.test.lib;

import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyClass {
    static int caclute = 0;
    static int maxCount = 100;//һ����100��Ʊ
    static int salePersonCount = 10;//10����Ʊ����
    static int buyPersonNumber = 300;//300������Ʊ

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 5, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10)
        );

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(5) * 1000);


                        if (finalI == 3) {

                            throw new NullPointerException("��ֹ");

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "postion:" + finalI);
                    caclute++;
                    if (caclute == 10) {
                        System.exit(0);
                    }
                }
            });

        }

    }

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) {
                try {
                    Thread.sleep(6000);    //	ʹ��ǰ������ 1 s��ȷ��������� t1.wait(); ִ��֮����ִ�� notify()
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " call notify()");
                // ���ѵ�ǰ��wait�߳�
                this.notifyAll();
            }
        }


    }


}
