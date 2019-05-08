package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LovesChangeCityFinder implements CityFinderStrategy {
  private static final int WEIGHT_IF_FIRST_NUBER_DIFFERENT = 100;
  private static final int WEIGHT_IF_NO_DIFFERENCE = -10;
  private static final int WEIGHT_IF_DIFFERENCE_NOT_FIRST = 20;
  private static final int DATAPOINTS = 40;
  private static final int DATAPOINTS_IN_DAY = 8;

  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    Double bestWeight = Double.MIN_VALUE;
    Optional<City> bestCity = Optional.empty();
    for (City city : candidateCities) {
      Double weight = 0d;
      int miniCounter = 0;
      Double tempSum = 0d;
      Double humidSum = 0d;
      List<Double> tempDiffs = new ArrayList<>();
      List<Double> humidDiffs = new ArrayList<>();
      List<Double> temps = city.getTemperatures();
      List<Double> humids = city.getHumidity();
      List<Integer> codes = city.getWeatherCodes();
      Integer lastCode = 0;
      Integer code;
      for (int i = 0; i < DATAPOINTS; i++) {
        tempSum += temps.get(i);
        humidSum += humids.get(i);
        miniCounter++;
        if (miniCounter == DATAPOINTS_IN_DAY) {
          miniCounter = 0;
          tempDiffs.add(tempSum);
          humidDiffs.add(humidSum);
          humidSum = 0d;
          tempSum = 0d;
        }
        code = codes.get(i);
        if (i > 0) {
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
      for (int i = 1; i < 5; i++) {
        weight += Math.abs(tempDiffs.get(i) - tempDiffs.get(i - 1));
        weight += Math.abs(humidDiffs.get(i) - humidDiffs.get(i - 1));
      } if (bestWeight < weight) {
        bestWeight = weight;
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
  }
}
