package ru.ulstu;

import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private List<Car> carsList;

    public CarDealership() {
        carsList = new ArrayList<>();
    }

    public void addCar(Car car) {
        try {
            if (carsList.stream().anyMatch(e -> e.getVIN().equals(car.getVIN()))) {
                throw new Exception();
            }
            this.carsList.add(car);
        }
        catch (Exception ex) {
            System.out.println("Такой автомобиль уже содержится в парке");
        }
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    // 1. Найти все машины указанного производителя
    public List<Car> findByManufacturer(String manufacturer) {
        return carsList.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .toList();
    }

    // 2. Вывести среднюю цену машин определённого типа
    public double getAveragePriceByType(CarType type) {
        return carsList.stream()
                .filter(car -> car.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }

    // 3. Вернуть список машин, отсортированных по году выпуска (от новых к старым)
    public List<Car> getCarsSortedByYear() {
        return carsList.stream()
                .sorted()
                .toList();
    }

    // 4. Статистика: Количество машин каждого типа
    public Map<CarType, Long> getCountByType() {
        return carsList.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    // 5. Статистика: Самая старая машина в наличии
    public Optional<Car> getOldestCar() {
        return carsList.stream()
                .min(Comparator.comparingInt(Car::getReleaseYear));
    }

    // 5. Статистика: Самая новая машина в наличии
    public Optional<Car> getNewestCar() {
        return carsList.stream()
                .max(Comparator.comparingInt(Car::getReleaseYear));
    }
}
