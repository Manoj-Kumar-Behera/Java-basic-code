package com.practice.oopsconecpt;

public class Car {

    private String carId;
    private String carName;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String carName, String model, double basePricePerDay) {
        this.carId = carId;
        this.carName = carName;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays){
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }
}
