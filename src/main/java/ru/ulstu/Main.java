package ru.ulstu;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static List<Car> cars = Arrays.asList(
            new Car("VIN1234567890ABC", "Camry", "Toyota", 2021, 35000, 25000.0f, CarType.SEDAN),
            new Car("VIN1234567890ABC", "Camry", "Toyota", 2021, 35000, 25000.0f, CarType.SEDAN),

            new Car("VIN9876543210XYZ", "Focus", "Ford", 2018, 62000, 15500.0f, CarType.HATCHBACK),
            new Car("VIN5555555555EEE", "Vesta", "Lada", 2017, 115000, 9000.0f, CarType.HATCHBACK),
            new Car("VIN7777777777QQQ", "M5", "BMW", 2023, 12000, 85000.0f, CarType.SUV),
            new Car("VIN7777777778QZX", "X5", "BMW", 2025, 1000, 109000.0f, CarType.SEDAN)
    );

    // Задание 1
    public static void arraysTask() {
        int[] releaseYears = new int[50];
        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            releaseYears[i] = r.nextInt(2000, 2026);
        }

        int[] res = Arrays.stream(releaseYears).filter(e -> e > 2015).toArray();
        System.out.print("Годы выпуска: ");
        for (int i = 0; i < res.length; i++) {
            System.out.printf("%d; ", res[i]);
        }
        System.out.println();

        int[] carAge = Arrays.stream(releaseYears).map(e -> LocalDate.now().getYear() - e).toArray();
        System.out.print("Возрасты автомобилей: ");
        for (int i = 0; i < carAge.length; i++) {
            System.out.printf("%d; ", carAge[i]);
        }
        System.out.println();
        float avgYear = (float)(Arrays.stream(carAge).sum() / releaseYears.length);
        System.out.printf("Средний возраст авто: %.02f", avgYear);
        System.out.println();
    }

    // Задание 2
    public static void collectionsTask() {
        List<String> models = Arrays.asList(
            "Camry", "M5", "Vesta", "Focus", "A6", "Camry", "E-Class", "Solaris", "M5", "Rio", "Coolray", "A6",
            "Qashqai", "Civic", "Jolion", "Focus", "Tahoe", "Golf", "Solaris", "Model S", "Tesla"
        );
        List<String> filteredModels = models.stream().distinct().sorted(Comparator.reverseOrder()).toList();
        System.out.print("Отсортированные модели: ");
        for (String filteredModel : filteredModels) {
            System.out.printf("%s; ", filteredModel);
        }
        System.out.println();

        Set<String> filteredModelsSet = filteredModels.stream().collect(Collectors.toSet());
        List<String> teslaRemoved = filteredModelsSet.stream().map(s -> s.replace("Tesla", "ELECTRIC_CAR")).collect(Collectors.toList());
        System.out.print("Модели из Set: ");
        for (String s : teslaRemoved) {
            System.out.printf("%s; ", s);
        }
    }

    // Задание 3
    public static void equalsTask() {

        HashSet<Car> carHashSet = new HashSet<>(cars);
        System.out.println(carHashSet);

        cars = cars.stream().sorted().toList();
        for (Car car : cars) {
            System.out.printf("%s; ", car.getReleaseYear());
        }
    }

    // Задание 4
    public static void analysisTask() {
        List<Car> under50kMiles = cars.stream().filter(car -> car.getMileage() < 50000).toList();
        System.out.printf("Пробег ниже 50к: ");
        for (int i = 0; i < under50kMiles.size(); i++) {
            System.out.printf("%s; ", under50kMiles.get(i).getMileage());
        }
        System.out.println();

        List<Car> priceSorted = cars.stream().sorted(Comparator.comparingDouble(Car::getPrice).reversed()).toList();
        System.out.printf("Топ-3 автомобиля по цене: ");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s|%.02f; ", priceSorted.get(i), priceSorted.get(i).getPrice());
        }
        System.out.println();

        float avgMileage = (float)(cars.stream().mapToInt(Car::getMileage).sum() / cars.size());
        System.out.printf("Средний пробег всех авто: %.02f", avgMileage);
        System.out.println();

        Map<String, List<Car>> carsGroupedByManufacturer = cars.stream().collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println(carsGroupedByManufacturer.get("BMW"));
    }

    // Доп задание с консольным интерфейсом.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        CarDealership dealership = new CarDealership();
        dealership.addCar(new Car("VIN111", "Camry", "Toyota", 2021, 35000, 25000.0f, CarType.SEDAN));
        dealership.addCar(new Car("VIN222", "RAV4", "Toyota", 2018, 62000, 28000.0f, CarType.SUV));
        dealership.addCar(new Car("VIN333", "Model 3", "Tesla", 2023, 12000, 45000.0f, CarType.ELECTRIC));

        System.out.println("=== Автоцентр готов к работе ===");

        while (isRunning) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти машины по производителю");
            System.out.println("3. Средняя цена по типу машины");
            System.out.println("4. Вывести машины от новых к старым");
            System.out.println("5. Показать статистику");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.println("\n[Добавление автомобиля]");
                        System.out.print("Введите VIN: ");
                        String vin = scanner.nextLine();
                        System.out.print("Введите модель: ");
                        String model = scanner.nextLine();
                        System.out.print("Введите производителя: ");
                        String manufacturer = scanner.nextLine();
                        System.out.print("Введите год выпуска: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите пробег: ");
                        int mileage = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите цену: ");
                        float price = Float.parseFloat(scanner.nextLine());

                        System.out.print("Выберите тип (SEDAN, SUV, ELECTRIC, HATCHBACK): ");
                        CarType type = CarType.valueOf(scanner.nextLine().toUpperCase());

                        Car newCar = new Car(vin, model, manufacturer, year, mileage, price, type);
                        dealership.addCar(newCar);
                    }
                    catch (Exception ex) {
                        System.out.println("Ошибка ввода. Попробуйте еще раз!");
                    }
                    break;

                case "2":
                    System.out.print("\nВведите имя производителя для поиска: ");
                    String searchBrand = scanner.nextLine();
                    List<Car> found = dealership.findByManufacturer(searchBrand);
                    if (found.isEmpty()) {
                        System.out.println("Машины такого производителя не найдены");
                    } else {
                        found.forEach(System.out::println);
                    }
                    break;

                case "3":
                    System.out.print("\nВведите тип для расчета средней цены (SEDAN, SUV и т.д.): ");
                    try {
                        CarType searchType = CarType.valueOf(scanner.nextLine().toUpperCase());
                        double avgPrice = dealership.getAveragePriceByType(searchType);
                        System.out.printf("Средняя цена для типа %s: %.2f\n", searchType, avgPrice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: Неверный тип автомобиля");
                    }
                    break;

                case "4":
                    System.out.println("\n[Список машин от новых к старым]:");
                    dealership.getCarsSortedByYear().forEach(System.out::println);
                    break;

                case "5":
                    System.out.println("\n=== СТАТиСТиКА АВТОЦЕНТРА ===");
                    System.out.println("Количество машин по типам:");
                    Map<CarType, Long> counts = dealership.getCountByType();
                    counts.forEach((t, count) -> System.out.println(" - " + t + ": " + count));

                    dealership.getOldestCar().ifPresentOrElse(
                            car -> System.out.println("Самая старая машина: " + car.getManufacturer() + " " + car.getModel() + " (" + car.getReleaseYear() + " г.)"),
                            () -> System.out.println("Самая старая машина: Нет данных")
                    );

                    dealership.getNewestCar().ifPresentOrElse(
                            car -> System.out.println("Самая новая машина: " + car.getManufacturer() + " " + car.getModel() + " (" + car.getReleaseYear() + " г.)"),
                            () -> System.out.println("Самая новая машина: Нет данных")
                    );
                    break;

                case "0":
                    System.out.println("Программа завершена");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Неверный пункт меню. Попробуйте еще раз");
            }
        }
        scanner.close();
    }
}