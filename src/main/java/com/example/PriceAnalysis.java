package com.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A utility class designed for the analysis of car prices, including the detection of outliers.
 */
public class PriceAnalysis {

  /**
   * Analyzes car prices to categorize them into regular data and outliers.
   *
   * @param cars List of Car objects to analyze.
   * @return A map containing the count of data within bounds and outliers.
   */
  public static Map<String, Long> analyzePrices(List<Car> cars) {
    // Extract and sort the prices of the cars.
    List<Integer> prices = cars.stream()
        .map(Car::getPrice)
        .sorted()
        .collect(Collectors.toList());

    // Calculate the first and third quartiles.
    int firstQuartile = calculatePercentile(prices, 25);
    int thirdQuartile = calculatePercentile(prices, 75);
    int interquartileRange = thirdQuartile - firstQuartile;

    // Calculate the price bounds for identifying outliers.
    double lowerBound = firstQuartile - 1.5 * interquartileRange;
    double upperBound = thirdQuartile + 1.5 * interquartileRange;

    // Partition cars into in-range and outlier based on price.
    Map<Boolean, Long> result = cars.stream()
        .collect(Collectors.partitioningBy(
            car -> car.getPrice() >= lowerBound && car.getPrice() <= upperBound,
            Collectors.counting()
        ));

    // Prepare final result map with counts of regular data and outliers.
    Map<String, Long> finalResult = new HashMap<>();
    finalResult.put("data", result.get(true));
    finalResult.put("outliers", result.get(false));

    return finalResult;
  }

  /**
   * Calculates a specific percentile value from a sorted list of prices.
   *
   * @param sortedPricesList Sorted list of prices.
   * @param percentile The desired percentile to calculate (e.g., 25 for the lower quartile).
   * @return The value corresponding to the given percentile.
   */
  private static int calculatePercentile(List<Integer> sortedPrices, int percentile) {
    int index = (int) Math.ceil(percentile / 100.0 * sortedPrices.size()) - 1;
    return sortedPrices.get(index);
  }
}