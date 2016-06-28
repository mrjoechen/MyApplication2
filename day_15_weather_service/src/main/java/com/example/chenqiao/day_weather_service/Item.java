package com.example.chenqiao.day_weather_service;

/**
 * Created by CHENQIAO on 2016/3/5.
 */
public class Item {

    String key;
    String value;

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
