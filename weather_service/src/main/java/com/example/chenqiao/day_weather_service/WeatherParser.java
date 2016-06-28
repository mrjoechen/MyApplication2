package com.example.chenqiao.day_weather_service;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Created by CHENQIAO on 2016/3/5.
 */
public class WeatherParser {

    public static Weather parserXml(InputStream in){



        Weather weather = null;
        //获取XmlPullParser
        XmlPullParser parser = Xml.newPullParser();
        //设置XmlPullParser的参数
        try {
            parser.setInput(in, "utf-8");
            //获取事件类型
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {

                switch (type) {
                    case XmlPullParser.START_TAG:   //解析开始标签
                        if ("resp".equals(parser.getName())) {
                            weather = new Weather();
                        } else if ("city".equals(parser.getName())) {
                            //获取city的数据
                            String city = parser.nextText();
                            weather.setCity(city);
                        } else if ("updatetime".equals(parser.getName())) {
                            //获取city的数据
                            String updatetime = parser.nextText();
                            weather.setUpdatetime(updatetime);
                        } else if ("wendu".equals(parser.getName())) {
                            //获取city的数据
                            String temp = parser.nextText();
                            weather.setTemp(temp);

                        } else if ("fengli".equals(parser.getName())) {
                            //获取city的数据
                            String wind = parser.nextText();
                            weather.setWind(wind);
                        } else if ("sunrise_1".equals(parser.getName())) {
                            //获取city的数据
                            String sunrise = parser.nextText();
                            weather.setSunrise(sunrise);
                        } else if ("sunset_1".equals(parser.getName())) {
                            //获取city的数据
                            String sunset = parser.nextText();
                            weather.setSunset(sunset);
                        } else if ("shidu".equals(parser.getName())) {
                            //获取city的数据
                            String humidity = parser.nextText();
                            weather.setHumidity(humidity);
                        }else if ("pm25".equals(parser.getName())) {
                            String pm25  = parser.nextText();
                            weather.setPm25(pm25);
                        }else if ("quality".equals(parser.getName())){
                            String quality = parser.nextText();
                            weather.setQuality(quality);
                        }else if ("suggest".equals(parser.getName())){
                            String suggest = parser.nextText();
                            weather.setSuggest(suggest);
                        }
                        break;
                    case XmlPullParser.END_TAG:     //解析结束标志
                        //判断要解析的结束标签
                        if ("resp".equals(parser.getName())) {
                            break;
                        }
                        break;
                }
                type = parser.next();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return weather;
    }

}

