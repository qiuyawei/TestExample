package com.wm.example.test.lib;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：
 */
public class Weather {
    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    private String weatherDesc;

    @Override
    public String toString() {
        return weatherDesc;
    }
}
