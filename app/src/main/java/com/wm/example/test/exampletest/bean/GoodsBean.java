package com.wm.example.test.exampletest.bean;

import android.os.Build;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：
 */
public class GoodsBean {
    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    private String goodsName;
    private String goodsPrice;
    private String goodsDesc;

    private GoodsBean(PersonBuild personBuild) {
        this.goodsName = personBuild.goodsName;
        this.goodsPrice = personBuild.goodsPrice;
        this.goodsDesc = personBuild.goodsDesc;
    }


    public static class PersonBuild {
        private String goodsName;
        private String goodsPrice;
        private String goodsDesc;


        public PersonBuild setGoodsName(String goodsName) {
            this.goodsName = goodsName;
            return this;
        }

        public PersonBuild setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
            return this;
        }

        public PersonBuild setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
            return this;
        }

        public GoodsBean create() {
            return new GoodsBean(this);
        }

    }

    @Override
    public String toString() {
        return "goodsName:" + goodsName + "\n"
                + "GoodsPrice:" + goodsPrice + "\n"
                + "GoodsDesc:" + goodsDesc + "\n";
    }
}
