package ee.taltech.iti0202.api.agency;

import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChoosingClient extends Client {
  public ChoosingClient(String name, String startingCity, CityFinderStrategy choosingStrategy) {
    super(name, startingCity, choosingStrategy);
  }

  public ChoosingClient(
      String name,
      String startingCity,
      CityFinderStrategy choosingStrategy,
      List<String> wantsToVisitCities
  ) {
    super(name, startingCity, choosingStrategy, wantsToVisitCities);
  }

  @Override
  public Optional<City> chooseBestCity(List<City> possibleCities) {
    if (possibleCities == null) {
      return Optional.empty();
    }
    List<City> wantCities = new ArrayList<>();
    for (City city : possibleCities) {
      if (wantsToVisitCities.contains(city.getName())) {
        wantCities.add(city);
      }
    }
    return choosingStrategy.findBestCity(wantCities);
  }
}
