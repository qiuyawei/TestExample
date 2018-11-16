package com.wm.example.test.exampletest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Author:qyw
 * on 2018/11/15.
 * QQ:448739075
 * 描述：
 */
public class Book implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String name;
    private int price;
    public Book(){}
    public Book(String name,int price){
        this.name=name;
        this.price=price;
    }
    protected Book(Parcel in) {
        in.writeString(name);
        in.writeInt(price);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            Book book=new Book();
            book.setName(in.readString());
            book.setPrice(in.readInt());
            return book;
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }
}
