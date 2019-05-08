package ee.taltech.iti0202.api.destinations;

import java.util.List;

public class CityBuilder {
  private String name;
  private Double lon;
  private Double lat;
  private List<Float> temperatures;
  private List<Float> humidity;
  private List<Integer> weatherCodes;

  public CityBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public CityBuilder setLon(Double lon) {
    this.lon = lon;
    return this;
  }

  public CityBuilder setLat(Double lat) {
    this.lat = lat;
    return this;
  }

  public CityBuilder setTemperatures(List<Float> temperatures) {
    this.temperatures = temperatures;
    return this;
  }

  public CityBuilder setHumidity(List<Float> humidity) {
    this.humidity = humidity;
    return this;
  }

  public CityBuilder setWeatherCodes(List<Integer> weatherCodes) {
    this.weatherCodes = weatherCodes;
    return this;
  }

  public City createCity() {
    return new City(name, lon, lat, temperatures, humidity, weatherCodes);
  }
}
