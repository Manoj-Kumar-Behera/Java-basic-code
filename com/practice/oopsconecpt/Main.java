package com.practice.oopsconecpt;

public class Main {

    public static void main(String[] args){
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        Car car1 = new Car("C001", "Honda", "Civic", 80.0);
        Car car2 = new Car("C002", "Hundai", "Sportz", 60.0);
        Car car3 = new Car("C003", "Porshe", "911", 200.0);

        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

        carRentalSystem.menu();
    }
}
