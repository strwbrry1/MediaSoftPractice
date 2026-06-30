package ru.ulstu;

import java.time.LocalDate;
import java.util.Objects;

public class Car implements Comparable<Car> {

    private String VIN;
    private String model;
    private String manufacturer;
    private int releaseYear;
    private int mileage;
    private double price;
    private CarType type;


    public Car(String VIN, String model, String manufacturer, int releaseYear, int mileage, float price, CarType type) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.releaseYear = releaseYear;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Car car = (Car) other;
        return Objects.equals(getVIN(), car.getVIN());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getVIN());
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", VIN='" + VIN + '\'' +
                '}';
    }

    @Override
    public int compareTo(Car other) {
        if (this.releaseYear > other.releaseYear) return -1;
        if (this.releaseYear < other.releaseYear) return 1;
        return 0;
    }
}
