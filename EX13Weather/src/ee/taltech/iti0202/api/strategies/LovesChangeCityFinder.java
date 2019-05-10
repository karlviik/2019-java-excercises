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
    Double bestWeight = -4000d;
    Optional<City> bestCity = Optional.empty();
    for (City city : candidateCities) {
      Double weight = 0d;
      List<Double> tempDiffs = new ArrayList<>();
      List<Double> humidDiffs = new ArrayList<>();
      // get temp and humid averages of days
      for (int i = 0; i < 5; i++) {
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
      // add absolute differences of humid and temp differences to weight
      for (int i = 1; i < 5; i++) {
        weight += (Math.abs(tempDiffs.get(i) - tempDiffs.get(i - 1))
            + Math.abs(humidDiffs.get(i) - humidDiffs.get(i - 1)));
      }
      // go through all weather codes and add weights based on difference between
      // this and previous code
      Integer lastCode = null;
      for (Integer code : city.getWeatherCodes()) {
        if (lastCode != null) {
          if (code.equals(lastCode)) {
            weight += NO_DIFFERENCE_WEIGHT;
          } else if (code / 100 != lastCode / 100) {
            weight += FIRST_NUMBER_DIFFERENT_WEIGHT;
          } else {
            weight += DIFFERENT_BUT_NOT_FIRST_WEIGHT;
          }
        }
        lastCode = code;
      }
      // if this city has higher weight, put it and its weight as best weight / city
      if (bestWeight <= weight) {
        bestWeight = weight;
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
  }
}
