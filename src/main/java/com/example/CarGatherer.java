package com.example;

import java.util.Optional;
import java.util.stream.Gatherer;
import lombok.AllArgsConstructor;

/**
 * A custom gatherer designed to skip a specified number of cars from a particular brand.
 * The gatherer processes the car stream while excluding certain cars based on their brand.
 */
@AllArgsConstructor
class CarGatherer implements Gatherer<Car, Optional<Car>, Car> {

  private int skipCount;
  private String skipBrand;

    /**
   * Integrator function that handles the logic of skipping cars with the specified brand.
   * The specified number of cars with the given brand will be excluded from the downstream processing.
   */
  @Override
  public Integrator<Optional<Car>, Car, Car> integrator() {
    return Gatherer.Integrator.of((_, element, downstream) -> {
      if (element.getCarBrand().equals(skipBrand) && skipCount > 0) {
        skipCount--;
        return true;
      }
      return downstream.push(element);
    });
  }
}