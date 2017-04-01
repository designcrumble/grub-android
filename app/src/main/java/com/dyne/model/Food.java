package com.dyne.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Divya on 3/16/2017.
 */

public class Food implements Parcelable {

    String name;
    String url;
    String price;
    String seat;

    public Food(String name,String url,String price,String seat)
    {
        this.name = name;
        this.url = url;
        this.price = price;
        this.seat = seat;
    }

    protected Food(Parcel in) {
        name = in.readString();
        url = in.readString();
        price = in.readString();
        seat = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(price);
        parcel.writeString(seat);
    }
}
