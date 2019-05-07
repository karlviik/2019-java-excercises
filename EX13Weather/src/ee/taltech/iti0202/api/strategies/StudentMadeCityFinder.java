package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentMadeCityFinder implements CityFinderStrategy {
  /**
   * Looge ise üks suvaline strateegia kasutades lambda väljendit.
   * @param candidateCities
   * @return place with highest temperature difference
   */
  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    return candidateCities.stream()
        .max(Comparator.comparing(x -> x.getTemperatures()
          .stream()
          .mapToDouble(z -> z)
          .max()
          .orElse(0)
          - x.getTemperatures()
          .stream()
          .mapToDouble(y -> y)
          .min()
          .orElse(0)));
  }
}
