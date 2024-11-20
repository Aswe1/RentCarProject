package org.example.rentcarproject.Entities;

public class Offer
{
    private int id;
    private int carId;
    private String customerName;
    private int rentalDays;
    private Double totalPrice;
    private Boolean accepted = false;
    private Boolean accidents = false;

    public Boolean getAccidents() {
        return accidents;
    }

    public void setAccidents(Boolean accidents) {
        this.accidents = accidents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
