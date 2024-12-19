package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * A custom collector that aggregates car prices and computes basic statistical metrics.
 * It calculates the minimum, maximum, average price, and standard deviation for the list of car prices.
 */
public class CarStatisticsCollector implements Collector<Car, List<Integer>, CarStatistics> {

  /**
   * Provides a new, empty list to collect car prices.
   *
   * @return A supplier that creates a new list.
   */    
  @Override
  public Supplier<List<Integer>> supplier() {
    return ArrayList::new;
  }

   /**
   * Accumulates the price of each car into the list.
   *
   * @return A bi-consumer that adds car prices to the accumulator.
   */ 
  @Override
  public BiConsumer<List<Integer>, Car> accumulator() {
    return (accumulator, car) -> accumulator.add(car.getPrice());
  }

  /**
   * Combines two lists of car prices into a single list.
   *
   * @return A binary operator that merges two price lists.
   */  
  @Override
  public BinaryOperator<List<Integer>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

   /**
   * Processes the list of car prices and calculates the statistical data.
   *
   * @return A function that computes the minimum, maximum, average, and standard deviation from the list.
   */ 
  @Override
  public Function<List<Integer>, CarStatistics> finisher() {
    return prices -> {
      int min = prices.stream().min(Integer::compare).orElse(0);
      int max = prices.stream().max(Integer::compare).orElse(0);
      double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
      double stdDev = Math.sqrt(prices.stream()
          .mapToDouble(price -> Math.pow(price - avg, 2))
          .average().orElse(0.0));
      return new CarStatistics(min, max, avg, stdDev);
    };
  }

  /**
   * Specifies the characteristics of the collector, indicating that it can be used concurrently.
   *
   * @return A set of characteristics for the collector.
   */  
  @Override
  public Set<Characteristics> characteristics() {
    Set<Characteristics> characteristics = new HashSet<>();
    characteristics.add(Characteristics.CONCURRENT);
    return characteristics;
  }
}