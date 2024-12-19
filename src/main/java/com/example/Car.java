package com.example;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a car with details such as brand, model, class, production date and price.
 */
@AllArgsConstructor
@Data
@Builder
public class Car {
  private String carBrand;
  private String carModel;
  private String carClass;
  private LocalDate productionDate;
  private int price;
}