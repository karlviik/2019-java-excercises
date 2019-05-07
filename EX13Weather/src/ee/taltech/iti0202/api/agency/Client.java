package ee.taltech.iti0202.api.agency;
import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Client {
  private String name;
  private String startingCity;
  CityFinderStrategy choosingStrategy;
  List<String> wantsToVisitCities;

  public Client(String name, String startingCity, CityFinderStrategy choosingStrategy) {
    this(name, startingCity, choosingStrategy, new ArrayList<>());
  }

  public Client(
      String name,
      String startingCity,
      CityFinderStrategy choosingStrategy,
      List<String> wantsToVisitCities
  ) {
    if (name == null || startingCity == null || choosingStrategy == null || wantsToVisitCities == null) {
      throw new InvalidParameterException();
    }
    this.name = name;
    this.startingCity = startingCity;
    this.choosingStrategy = choosingStrategy;
    this.wantsToVisitCities = wantsToVisitCities;
  }


  public String getName() {
    return name;
  }

  public String getStartingCity() {
    return startingCity;
  }

  public CityFinderStrategy getChoosingStrategy() {
    return choosingStrategy;
  }

  public List<String> getCitiesThatWantsToVisit() {
    return wantsToVisitCities;
  }

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
    Optional<City> wantCity = choosingStrategy.findBestCity(wantCities);
    if (wantCity.isEmpty()) {
      wantCity = choosingStrategy.findBestCity(possibleCities);
      System.out.println(possibleCities.get(39999999));
      return wantCity;
    } else {
      return wantCity;
    }
  }
}

