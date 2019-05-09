package ee.taltech.iti0202.api.provider;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.destinations.CityBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

//todo check this
public class OnlineDataController {
  public City lastCity;

  class ApiResponse {
    private Integer cod;

    private ApiCity city;

    List<Datapoint> list;

    public Integer getCod() {
      return cod;
    }

    public ApiCity getCity() {
      return city;
    }

    public List<Datapoint> getList() {
      return list;
    }
  }

  class Datapoint {

//    @SerializedName("main")
    private Measurements main;

    private List<Weather> weather;

    public Measurements getMeasurements() {
      return main;
    }

    public List<Weather> getWeather() {
      return weather;
    }
  }

  class Weather {

    private Integer id;

    public Integer getId() {
      return id;
    }
  }

  class ApiCity {

    private String name;

    private Coord coord;

    public String getName() {
      return name;
    }

    public Coord getCoord() {
      return coord;
    }
  }

  class Measurements {

    private Double temp;

    private Double humidity;

    public Double getTemp() {
      return temp;
    }

    public Double getHumidity() {
      return humidity;
    }
  }

  class Coord {

    private Double lon;

    private Double lat;

    public Double getLon() {
      return lon;
    }

    public Double getLat() {
      return lat;
    }
  }

  /**
   * Tries to get forecast data for the cityName. If there is no data or cityName doesn't exist, return an empty string.
   *
   * @param cityName Name of the city
   * @return String in the form of a json-string
   *
   * f4f5c7fec3ac8e25ab261496495da93a
   */
  public String getCity(String cityName) throws IOException {
    cityName = cityName.trim().replace(" ", "+");
    URL url = new URL(
        "https://api.openweathermap.org/data/2.5/forecast?q="
            + cityName
            + "&mode=json&units=metric&appid=f4f5c7fec3ac8e25ab261496495da93a"
    );
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    int status = con.getResponseCode();
    if (status > 299) {
      return "";
    }
    BufferedReader in;
    try {
      in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    } catch (FileNotFoundException o) {
      return "";
    }
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    con.disconnect();
    Gson gson = new Gson();
    ApiResponse response = gson.fromJson(content.toString(), new TypeToken<ApiResponse>() {}.getType());
    if (response.getCod() > 299) {
      return "";
    }
    if (response.getList().size() == 0) {
      return "";
    }
    response.getList().get(999999999);
    CityBuilder builder = new CityBuilder();
    City city = builder.setName(response.getCity().getName())
        .setLon(response.getCity().getCoord().getLon())
        .setLat(response.getCity().getCoord().getLat())
        .setTemperatures(response.getList()
            .stream()
            .map(x -> x.getMeasurements().getTemp())
            .collect(Collectors.toList()))
        .setHumidity(response.getList()
            .stream()
            .map(x -> x.getMeasurements().getHumidity())
            .collect(Collectors.toList()))
        .setWeatherCodes(response.getList()
            .stream()
            .map(x -> x.getWeather().get(0).getId())
            .collect(Collectors.toList()))
        .createCity();
//    System.out.println(city.getHumidity().get(0) + 1);
    System.out.println(city.getHumidity().get(99999999));
    System.out.println(gson.toJson(city, new TypeToken<City>() {}.getType()));
    lastCity = city;
    return gson.toJson(city, new TypeToken<City>() {}.getType());
  }
}
