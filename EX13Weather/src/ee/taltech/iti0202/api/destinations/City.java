package ee.taltech.iti0202.api.destinations;

import java.util.Arrays;
import java.util.List;

public class City {
  private String name;
  private double lon;
  private double lat;
  private List<Double> temperatures;
  private List<Double> humidity;
  private List<Integer> weatherCodes;

  City(String name, Double lon, Double lat, List<Double> temperatures, List<Double> humidity, List<Integer> weatherCodes) {
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

  public List<Double> getTemperatures() {
    return temperatures;
  }

  public List<Double> getHumidity() {
    return humidity;
  }

  public List<Integer> getWeatherCodes() {
    return weatherCodes;
  }

  public double getAverageTemperature() {
    return temperatures.stream()
        .mapToDouble(x -> x)
        .average()
        .orElse(0);
  }

  public double getAverageHumidity() {
    return humidity.stream().mapToDouble(Double::doubleValue).average().orElse(0);

  }

}
