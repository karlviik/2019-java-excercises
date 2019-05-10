package ee.taltech.iti0202.api.agency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.destinations.CityBuilder;
import ee.taltech.iti0202.api.provider.OnlineDataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TravelAgency {

  private List<String> cityNames;
  private OnlineDataController dataController;

  public TravelAgency(List<String> cityNames, OnlineDataController dataController) {
    this.cityNames = cityNames;
    this.dataController = dataController;
  }

  /**
   * If the agency doesn't have a destination city yet, adds it to the list.
   *
   * @param city city name.
   */
  public void addCity(String city) {
    if (!cityNames.contains(city)) {
      cityNames.add(city);
    }
  }

  /**
   * Return all the cities that the agency has.
   *
   * @return list of cities
   */
  public List<String> getCityList() {
    return cityNames;
  }

  //TODO check this
  /**
   * This method tries to find a suitable city for the client to visit.
   *
   * It uses OnlineDataController, to get data for the cities.
   * After getting data about a city, SAVE IT for the duration of the cycle.
   * OpenWeather API updates data every 10 minutes.
   * Create a City object using the CityBuilder here.
   * @param client a client who wants to go somewhere.
   * @return Optional city if the client was happy with it.
   */
  public Optional<City> findSuitableCitiesForClient(Client client) throws IOException {
    List<City> cities = new ArrayList<>();
    Gson gson = new Gson();
    for (String cityName : cityNames) {
      if (!cityName.equals(client.getStartingCity())) {
        String response = dataController.getCity(cityName);
        if (!response.equals("")) {
          OnlineDataController.ApiResponse responseObject = gson.fromJson(response, new TypeToken<OnlineDataController.ApiResponse>() {}.getType());
          CityBuilder builder = new CityBuilder();
          City city = builder.setName(responseObject.getCity().getName())
              .setLon(responseObject.getCity().getCoord().getLon())
              .setLat(responseObject.getCity().getCoord().getLat())
              .setTemperatures(responseObject.getList()
                  .stream()
                  .map(x -> x.getMeasurements().getTemp())
                  .collect(Collectors.toList()))
              .setHumidity(responseObject.getList()
                  .stream()
                  .map(x -> x.getMeasurements().getHumidity())
                  .collect(Collectors.toList()))
              .setWeatherCodes(responseObject.getList()
                  .stream()
                  .map(x -> x.getWeather().get(0).getId())
                  .collect(Collectors.toList()))
              .createCity();
          cities.add(city);
        }
      }
    }
//    System.out.println(cities.get(0).getHumidity());
////    System.out.println(cities.get(9999999).getName());
//    System.out.println(cities.get(1).getTemperatures());
//    System.out.println(cities.get(0).getAverageHumidity());
//    System.out.println(cities.get(0).getHumidity());
//    System.out.println(cities.get(0).getLat());
//    System.out.println(cities.get(0).getLon());
//    System.out.println(cities.get(0).getWeatherCodes());

//    Optional<City> wasd = client.chooseBestCity(cities);

    return client.chooseBestCity(cities);
  }

}
