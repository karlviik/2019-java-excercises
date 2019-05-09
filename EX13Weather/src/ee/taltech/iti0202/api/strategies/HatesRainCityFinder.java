package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HatesRainCityFinder implements CityFinderStrategy {
  private static final int DATAPOINTS = 40;
  private static final int DATAPOINTS_IN_DAY = 8;
  /**
   * Klient eelistab linna, kus on kõige vähem sajuga päevi.
   * Kui sama sajupäevadega linnu on mitu, siis eelistab sellist, kus keskmine õhuniiskus on väikseim
   */
  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    List<City> possibleCities = new ArrayList<>();
    int minRainyDayCount = Integer.MAX_VALUE;
    for (City city : candidateCities) {
      int miniCounter = 0;
      List<Integer> codes = city.getWeatherCodes();
      int rainyDayCount = 0;
      boolean hasRainedToday = false;
      for (int i = 0; i < DATAPOINTS; i++) {
        if (!hasRainedToday && codes.get(i) / 100 == 5) {
          hasRainedToday = true;
          rainyDayCount++;
        }
        miniCounter++;
        if (miniCounter == DATAPOINTS_IN_DAY) {
          miniCounter = 0;
          hasRainedToday = false;
        }
      }
      if (minRainyDayCount == rainyDayCount) {
        possibleCities.add(city);
      } else if (rainyDayCount < minRainyDayCount) {
        possibleCities = new ArrayList<>(List.of(city));
        minRainyDayCount = rainyDayCount;
      }
    }
    return possibleCities.stream().min(Comparator.comparing(City::getAverageHumidity));
  }
}
