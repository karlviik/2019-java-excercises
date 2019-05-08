package ee.taltech.iti0202.api.destinations;

import java.util.List;

public class City {
  private String name;
  private Double lon;
  private Double lat;
  private List<Float> temperatures;
  private List<Float> humidity;
  private List<Integer> weatherCodes;

  City(
      String name,
      Double lon,
      Double lat,
      List<Float> temperatures,
      List<Float> humidity,
      List<Integer> weatherCodes
  ) {
    this.name = name;
    this.lon = lon;
    this.lat = lat;
    this.temperatures = temperatures;
    this.humidity = humidity;
    this.weatherCodes = weatherCodes;
  }

  public String getName() {
    return name;
  }

  public Double getLon() {
    return lon;
  }

  public Double getLat() {
    return lat;
  }

  public List<Float> getTemperatures() {
    return temperatures;
  }

  public List<Float> getHumidity() {
    return humidity;
  }

  public List<Integer> getWeatherCodes() {
    return weatherCodes;
  }

  public Double getAverageTemperature() {
    return temperatures.stream()
        .mapToDouble(x -> x)
        .average()
        .orElse(0);
  }

  public Double getAverageHumidity() {
    return humidity.stream()
        .mapToDouble(x -> x)
        .average()
        .orElse(0);

  }

}
