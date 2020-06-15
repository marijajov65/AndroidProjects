package com.example.proj4jovicic;

public class House {
    public int id;
    public float x, y;
    public String address;
    public String city;
    public float bedrooms;
    public float bathrooms;
    public float price;

    public House(int id,float x, float y, String address, String city, float bedrooms, float bathrooms, float price) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.address = address;
        this.city = city;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + x + " " + y + " " + address + " " + city + " " + bedrooms + " " + " " + bathrooms + " " + price;
    }
}