package com.example.test.models;

public class GoiY {
    private int ID;
    private String weather;
    private String goiY;

    public GoiY(int ID, String weather, String goiY) {
        this.ID = ID;
        this.weather = weather;
        this.goiY = goiY;
    }

    public GoiY() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getGoiY() {
        return goiY;
    }

    public void setGoiY(String goiY) {
        this.goiY = goiY;
    }
}
