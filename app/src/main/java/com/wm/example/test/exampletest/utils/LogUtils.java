package com.wm.example.test.exampletest.utils;

import android.util.Log;

/**
 * Created by Author:qyw
 * on 2018/10/25.
 * QQ:448739075
 * 描述：
 */
public class LogUtils {
    private static final String TAG = "TESET_EX";
    private static boolean isOpen = true;//是否输出日志

    public static void i(String content) {
        if (isOpen)
            Log.i(TAG, getContent(content,4));
    }

    public static void d(String content) {
        if (isOpen)
            Log.i(TAG, content);
    }

    public static void PrintD(String tag, String content, Object... args) {
        Log.d(tag, getContent(content, 4, args));
    }

    private static String getNameFromTrace(StackTraceElement[] traceElements, int place) {
        StringBuilder taskName = new StringBuilder();
        if (traceElements != null && traceElements.length > place) {
            StackTraceElement traceElement = traceElements[place];
            taskName.append(traceElement.getMethodName());
            taskName.append("(").append(traceElement.getFileName()).append(":").append(traceElement.getLineNumber()).append(")");
        }
        return taskName.toString();
    }

    private static String getContent(String msg, int place, Object... args) {
        try {
            String sourceLinks = getNameFromTrace(Thread.currentThread().getStackTrace(), place);
            return sourceLinks + String.format(msg, args);
        } catch (Throwable throwable) {
            return msg;
        }
    }


}
