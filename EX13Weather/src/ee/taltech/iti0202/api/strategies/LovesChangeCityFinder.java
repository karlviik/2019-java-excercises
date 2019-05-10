package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LovesChangeCityFinder implements CityFinderStrategy {
  private static final int FIRST_NUMBER_DIFFERENT_WEIGHT = 100;
  private static final int NO_DIFFERENCE_WEIGHT = -10;
  private static final int DIFFERENT_BUT_NOT_FIRST_WEIGHT = 20;

  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    Double bestWeight = Double.MIN_VALUE;
    Optional<City> bestCity = Optional.empty();
    for (City city : candidateCities) {
      Double weight = 0d;
      List<Double> tempDiffs = new ArrayList<>();
      List<Double> humidDiffs = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        System.out.println(city.getTemperatures().subList(8 * i, 8 * (i + 1)));
        tempDiffs.add(city.getTemperatures()
            .subList(8 * i, 8 * (i + 1))
            .stream()
            .mapToDouble(x -> x)
            .average()
            .getAsDouble());
//            .orElse(0));
        humidDiffs.add(city.getHumidity()
            .subList(8 * i, 8 * (i + 1))
            .stream()
            .mapToDouble(x -> x)
            .average()
            .getAsDouble());
//            .orElse(0));
      }
//      for (int i = 1; i < 5; i++) {
//        weight += (Math.abs(tempDiffs.get(i) - tempDiffs.get(i - 1))
//            + Math.abs(humidDiffs.get(i) - humidDiffs.get(i - 1)));
//      }
//      System.out.println(city.getName() + " " + weight);
      Integer lastCode = null;
//      System.out.println(city.getWeatherCodes());
//      int a = 0;
//      int b = 0;
      for (Integer code : city.getWeatherCodes()) {
        if (lastCode != null) {
          if (code.equals(lastCode)) {
            weight += NO_DIFFERENCE_WEIGHT;
//            a++;
          } else if (code / 100 != lastCode / 100) {
            weight += FIRST_NUMBER_DIFFERENT_WEIGHT;
          } else {
            weight += DIFFERENT_BUT_NOT_FIRST_WEIGHT;
//            b++;
          }
        }
        lastCode = code;
      }
      weight = Math.round(weight * 100.0) / 100.0;
      System.out.println(city.getName() + " " + weight);
      if (bestWeight < weight) {
        bestWeight = weight;
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
  }
}
