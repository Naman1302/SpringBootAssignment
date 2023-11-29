package com.DI.assignment.Entity;

public class Address {
    private int houseNumber;
    private String city;
    private String state;

    public Address() {
    }
    public Address(int houseNumber,String city,String state) {
        this.houseNumber=houseNumber;
        this.city=city;
        this.state=state;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public boolean isBlank() {
        return this.houseNumber <= 0 || this.city.isEmpty() || this.state.isEmpty();
    }
}
