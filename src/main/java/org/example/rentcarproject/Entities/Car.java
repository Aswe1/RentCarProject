package org.example.rentcarproject.Entities;

public class Car {
    private int id;
    private String model;
    private String location;
    private Double pricePerDay;
    private Boolean isDeleted = false;
    private Boolean isRented = false;

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getDailyPrice() {
        return pricePerDay;
    }

    public void setDailyPrice(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
