package com.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The main class responsible for demonstrating various operations on a list of Car objects,
 * including filtering, grouping by class, statistical analysis, and price distribution analysis.
 */
public class Main {

  /**
   * The entry point of the program. It runs the entire process of car generation,
   * filtering, grouping, and analysis.
   */
  public static void main(String[] args) {
    int N = 10;
    String brandToSkip = "Toyota";

    // Generate a stream of cars, filter them by the specified criteria, and print the result
    List<Car> cars = getFilteredCars(N, brandToSkip);
    for (Car car : cars) {
      System.out.println(car);
    }

    // Group the filtered cars by their class and print the count of each class
    Map<String, List<Car>> groupedCars = getGroupedCars(cars);
    for (Map.Entry<String, List<Car>> entry : groupedCars.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue().size());
    }

    // Calculate and display the statistical data for the car prices
    CarStatistics statistics = cars.stream()
        .collect(new CarStatisticsCollector());
    System.out.println(statistics);

    // Perform price analysis to identify outliers and display the result
    Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(cars);
    System.out.println(priceAnalysis);
  }

  /**
   * Filters the cars based on the number of cars to skip and the brand to avoid.
   *
   * @param skipCount The number of cars of a specified brand to skip.
   * @param brandToSkip The brand of cars to exclude from the filtered list.
   * @return A list of filtered Car objects.
   */
  private static List<Car> getFilteredCars(int skipCount, String brandToSkip) {
    return CarGenerator.generateCarStream()
        .gather(new CarGatherer(skipCount, brandToSkip))
        .limit(500)
        .collect(Collectors.toList());
  }

  /**
   * Groups the cars by their class and filters them based on their production date.
   *
   * @param cars The list of cars to be grouped and filtered.
   * @return A map where the keys are car classes and values are lists of cars in each class.
   */
  private static Map<String, List<Car>> getGroupedCars(List<Car> cars) {
    return cars.stream()
        .filter(car -> car.getProductionDate().isAfter(LocalDate.now().minusMonths(60)))
        .collect(Collectors.groupingBy(Car::getCarClass));
  }
}