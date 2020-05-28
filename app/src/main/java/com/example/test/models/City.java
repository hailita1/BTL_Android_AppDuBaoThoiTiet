package com.example.test.models;

public class City {
    private int ID;
    private String City_Name;
    private String Country;
    private String Lat;
    private String Lng;


    public City(int ID, String city_Name, String country, String lat, String lng) {
        this.ID = ID;
        City_Name = city_Name;
        Country = country;
        Lat = lat;
        Lng = lng;
    }

    public City() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }
}
