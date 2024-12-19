package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A container for statistical data related to car prices, including the minimum, maximum,
 * average price and standard deviation of prices.
 */
@AllArgsConstructor
@Data
public class CarStatistics {

  private int minimumPrice;
  private int maximumPrice;
  private double averagePrice;
  private double standardDeviation;
}