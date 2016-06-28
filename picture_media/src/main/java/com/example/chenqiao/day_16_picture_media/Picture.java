package com.example.chenqiao.day_16_picture_media;

import android.media.ExifInterface;

/**
 * Created by CHENQIAO on 2016/3/12.
 */
public class Picture {


    String date ;
    String width ;
    String height ;
    String make ;
    String model ;
    String iso ;

    public Picture(String date, String height, String iso, String make, String model, String width) {
        this.date = date;
        this.height = height;
        this.iso = iso;
        this.make = make;
        this.model = model;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "date='" + date + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", iso='" + iso + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
