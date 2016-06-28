package com.example.chenqiao.day_12_weather_homework;

/**
 * Created by CHENQIAO on 2016/3/5.
 */
public class Weather {

    String city;
    String temp;
    String wind;
    String sunrise;
    String sunset;
    String humidity;
    String updatetime;
    String pm25;
    String quality;
    String suggest;

    public Weather() {
    }



    @Override
    public String toString() {
        return "Weather info:" +"\n"+
                "城市:" + city + "\n" +
                "当前温度:" + temp + "\n" +
                "风力:" + wind + "\n" +
                "日出时间:" + sunrise + "\n" +
                "日落时间:" + sunset +"\n" +
                "湿度:" + humidity +"\n"+
                "更新时间:"+updatetime+"\n"+
                "pm2.5:"+pm25+"\n"+
                "空气质量:"+quality;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
