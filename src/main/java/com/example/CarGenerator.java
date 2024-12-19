package com.example;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Generates random Car instances using the Stream API, providing a continuous flow of data.
 * The generator creates various car details including brand, model, class, manufacture date, and price.
 */
public class CarGenerator {

    private static final String[] CAR_BRANDS = {"Chevrolet", "Nissan", "Volkswagen", "Mazda", "Hyundai"};
    private static final String[] CAR_MODELS = {"Cruze", "Altima", "Golf", "CX-5", "Sonata"};
    private static final String[] CAR_CLASSES = {"Compact", "Sedan", "Crossover", "Coupe", "Hatchback"};  
    private static final Random RANDOM = new Random();

  /**
   * Creates a stream of randomly generated Car objects, simulating a continuous stream of car data.
   *
   * @return A stream containing randomly generated Car objects.
   */
  public static Stream<Car> generateCarStream() {
    return Stream.generate(() -> Car.builder()
        .carBrand(getRandomCarBrand())
        .carModel(getRandomCarModel())
        .carClass(getRandomCarClass())
        .productionDate(LocalDate.now().minusMonths(RANDOM.nextInt(240)))
        .price((int) (200_000 + (1_000_000 * RANDOM.nextDouble())))
        .build());
  }

  /**
   * Selects a random Car brand from the predefined list of car brands.
   *
   * @return A randomly chosen Car brand.
   */
  private static String getRandomCarBrand() {
    return CAR_BRANDS[RANDOM.nextInt(CAR_BRANDS.length)];
  }

  /**
   * Selects a random Car model from the predefined list of car models.
   *
   * @return A randomly chosen Car model.
   */
  private static String getRandomCarModel() {
    return CAR_MODELS[RANDOM.nextInt(CAR_MODELS.length)];
  }

  /**
   * Selects a random Car class from the predefined list of car classes.
   *
   * @return A randomly chosen Car class.
   */
  private static String getRandomCarClass() {
    return CAR_CLASSES[RANDOM.nextInt(CAR_CLASSES.length)];
  }
}