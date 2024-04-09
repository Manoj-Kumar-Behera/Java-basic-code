package com.practice.oopsconecpt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }else {
            System.out.println("Car: "+ car + " is not available..");
        }
    }

    public void returnCar(Car car){
        Rental rentalToRemove = null;
        for(Rental rental: rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
            car.returnCar();
            System.out.println("Car: "+ car.getCarName()+" "+ car.getModel() +" returned Successfully..");
        }else {
            System.out.println("Car was not rented..");
        }
    }

    public void menu(){
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.println("=========Car Rental System========");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                    System.out.println("===Rent a Car=====");
                    System.out.println("Enter your name");
                    String customerName = scanner.nextLine();
                    System.out.println("====Available Cars=====");
                    for(Car car:cars){
                        if(car.isAvailable()){
                            System.out.println("Car ID: "+ car.getCarId() +" Cars: "+ car.getCarName() +" Model: "+car.getModel());
                        }
                    }
                    System.out.println("Enter the car id you want to rent:: ");
                    String carId = scanner.nextLine();

                    System.out.println("Enter the number of days you want to rent:: ");
                    int rentalDays = scanner.nextInt();
                    scanner.nextLine();

                    Customer newCustomer = new Customer("CUS"+(customers.size() + 1), customerName);
                    addCustomer(newCustomer);
                    Car selectedCar = null;
                    for(Car car: cars){
                        if(car.getCarId().equals(carId) && car.isAvailable()){
                            selectedCar = car;
                            break;
                        }
                    }
                    if(selectedCar != null){
                        double totalPrice = selectedCar.calculatePrice(rentalDays);
                        System.out.println("===Rental Information===");
                        System.out.println("Customer Id: " + newCustomer.getCustomerId());
                        System.out.println("Customer Name: " + newCustomer.getCustomerName());
                        System.out.println("Car: " + selectedCar.getCarName() + " model: "+ selectedCar.getModel());
                        System.out.println("Rental Days: "+ rentalDays);
                        System.out.printf("Total Price: $%.2f%n", totalPrice);

                        System.out.println("Please confirm your rental(Y/N) ");
                        String confirm = scanner.nextLine();

                        if(confirm.equalsIgnoreCase("Y")){
                            rentCar(selectedCar,newCustomer,rentalDays);
                            System.out.println("Car "+selectedCar.getCarName()+ " has been rented to "+ newCustomer.getCustomerName() + " successfully for "+rentalDays+" days..");
                        }else {
                            System.out.println("Invalid car selection or car not available for rent..");
                        }
                    }


            } else if (choice == 2) {
                System.out.println("====Return a car=====");
                System.out.println("Enter the car id you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for(Car car: cars){
                    if(car.getCarId().equals(carId) && !car.isAvailable()){
                        carToReturn = car;
                        break;
                    }
                }
                if(carToReturn != null){
                    Customer customer = null;
                    for(Rental rental: rentals){
                        if(rental.getCar() == carToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if(customer != null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successful by "+ customer.getCustomerName());
                    }else {
                        System.out.println("Car was not rented or rental information missing..");
                    }
                }else {
                    System.out.println("Invalid Car id or car is not rented..");
                }
            } else if (choice == 3) {
                break;
            }else {
                System.out.println("Invalid choice. Please enter valid choice..");
            }
        }

        System.out.println("Thank you for using the car rental system..");
    }
}
