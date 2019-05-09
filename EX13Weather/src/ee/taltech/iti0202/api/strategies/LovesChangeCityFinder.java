package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LovesChangeCityFinder implements CityFinderStrategy {
  private static final int WEIGHT_IF_FIRST_NUBER_DIFFERENT = 100;
  private static final int WEIGHT_IF_NO_DIFFERENCE = -10;
  private static final int WEIGHT_IF_DIFFERENCE_NOT_FIRST = 20;

  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    Double bestWeight = Double.MIN_VALUE;
    Optional<City> bestCity = Optional.empty();
    for (City city : candidateCities) {
      Double weight = 0d;
      List<Double> tempDiffs = new ArrayList<>();
      List<Double> humidDiffs = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        tempDiffs.add(city.getTemperatures()
            .subList(8 * i, 8 * i - 1)
            .stream()
            .mapToDouble(x -> x)
            .average()
            .orElse(0));
        humidDiffs.add(city.getHumidity()
            .subList(8 * i, 8 * i - 1)
            .stream()
            .mapToDouble(x -> x)
            .average()
            .orElse(0));
      }
      for (int i = 1; i < 5; i++) {
        weight += Math.abs(tempDiffs.get(i) - tempDiffs.get(i - 1))
            + Math.abs(humidDiffs.get(i) - humidDiffs.get(i - 1));
      }
      Integer lastCode = null;
      for (Integer code : city.getWeatherCodes()) {
        if (lastCode != null) {
          if (code.equals(lastCode)) {
            weight += WEIGHT_IF_NO_DIFFERENCE;
          } else if (code / 100 != lastCode / 100) {
            weight += WEIGHT_IF_FIRST_NUBER_DIFFERENT;
          } else {
            weight += WEIGHT_IF_DIFFERENCE_NOT_FIRST;
          }
        }
        lastCode = code;
      }
      if (bestWeight < weight) {
        bestWeight = weight;
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
  }
}
