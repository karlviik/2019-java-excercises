package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WarmWeatherCityFinder implements CityFinderStrategy {
  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    Optional<City> bestCity = Optional.empty();
    Double bestValue = Double.MIN_VALUE;
    for (City city : candidateCities) {
      if (city != null && city.getAverageTemperature() != null && city.getAverageTemperature() > bestValue) {
        bestValue = city.getAverageTemperature();
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
//    return candidateCities.stream()
//        .max(Comparator.comparing(x -> x.getAverageTemperature()));
  }
}
