package com.example.aceahmer.quakequake;

public class DataModel {
    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }

    public DataModel(double magnitude, String place, long millisecond, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.millisecond = millisecond;
        this.url = url;
    }

    private double magnitude;
    private String place;
    private long millisecond;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
