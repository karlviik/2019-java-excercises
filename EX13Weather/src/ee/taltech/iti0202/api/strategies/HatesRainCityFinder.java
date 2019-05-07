package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HatesRainCityFinder implements CityFinderStrategy {
  // TODO: this

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
      Double sum = 0d;
      List<Double> temps = city.getTemperatures();
      List<Integer> codes = city.getWeatherCodes();
      int rainyDayCount = 0;
      boolean hasRainedToday = false;
      for (int i = 0; i < 40; i++) {
        sum += temps.get(i);
        if (!hasRainedToday && codes.get(i) / 100 == 5) {
          hasRainedToday = true;
          rainyDayCount++;
        }
        miniCounter++;
        if (miniCounter == 8) {
          miniCounter = 0;
          hasRainedToday = false;
          if (sum / 8 >= 80) {
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
