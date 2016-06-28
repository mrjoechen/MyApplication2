package com.example.chenqiao.day_12_xml_demo;

/**
 * Created by CHENQIAO on 2016/3/5.
 */
public class PhoneNumberInfo {

    String number;
    String areanumber;
    String attribution;
    String operators;
    String status;

    public PhoneNumberInfo() {

    }

    public PhoneNumberInfo(String number, String areanumber, String attribution, String operators, String status) {
        this.number = number;
        this.areanumber = areanumber;
        this.attribution = attribution;
        this.operators = operators;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PhoneNumberInfo:" +"\n"+ "number:" + number +"\n"+
                 "areanumber:" + areanumber +"\n"+
                "attribution:" + attribution +"\n" +
                "operators:" + operators+"\n"  +
                "status:" + status +"\n";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAreanumber() {
        return areanumber;
    }

    public void setAreanumber(String areanumber) {
        this.areanumber = areanumber;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getOoperators() {
        return operators;
    }

    public void setoperators(String ooperators) {
        this.operators = ooperators;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
