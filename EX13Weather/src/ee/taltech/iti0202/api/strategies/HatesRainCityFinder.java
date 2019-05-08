package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HatesRainCityFinder implements CityFinderStrategy {
  private static final int DATAPOINTS = 40;
  private static final int DATAPOINTS_IN_DAY = 8;
  private static final double MAXIMUM_ALLOWED_HUMIDITY = 80d;
  /**
   * Klient ei soovi rohkemal kui ühel päeval vihma ning keskmine niiskus ei tohi olla ühelgi päeval üle 80%.
   * @param candidateCities
   * @return
   */
  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    List<City> possibleCities = new ArrayList<>();
    for (City city : candidateCities) {
      boolean willAdd = true;
      int miniCounter = 0;
      Float sum = 0f;
      List<Float> temps = city.getTemperatures();
      List<Integer> codes = city.getWeatherCodes();
      int rainyDayCount = 0;
      boolean hasRainedToday = false;
      for (int i = 0; i < DATAPOINTS; i++) {
        sum += temps.get(i);
        if (!hasRainedToday && codes.get(i) / 100 == 5) {
          hasRainedToday = true;
          rainyDayCount++;
        }
        miniCounter++;
        if (miniCounter == DATAPOINTS_IN_DAY) {
          miniCounter = 0;
          hasRainedToday = false;
          if (sum / DATAPOINTS_IN_DAY >= MAXIMUM_ALLOWED_HUMIDITY) {
            willAdd = false;
            break;
          }
        }
      }
      if (willAdd && rainyDayCount < 2) {
        return Optional.of(city);
      }
    }
    return Optional.empty();
  }
}
