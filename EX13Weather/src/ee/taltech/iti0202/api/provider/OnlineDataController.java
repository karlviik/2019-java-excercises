package ee.taltech.iti0202.api.provider;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.destinations.CityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineDataController {
  class ApiResponse {

    private ApiCity city;

    class ApiCity {

      private String name;

      private Coord coord;

      class Coord {

        Double lon;

        Double lat;
      }
    }

    List<Datapoint> list;

    class Datapoint {

      @SerializedName("main")
      private Measurements measurements;

      class Measurements {

        private Double temp;

        private Double humidity;
      }

      private List<Weather> weather;

      class Weather {

        private Integer id;
      }
    }
  }

  /**
   * Tries to get forecast data for the cityName. If there is no data or cityName doesn't exist, return an empty string.
   *
   * @param cityName Name of the city
   * @return String in the form of a json-string
   *
   *
   *
   * f4f5c7fec3ac8e25ab261496495da93a
   */
  public String getCity(String cityName) throws IOException {
    cityName = cityName.replace(" ", "+");
    URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=f4f5c7fec3ac8e25ab261496495da93a");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    con.disconnect();
    Gson gson = new Gson();
    ApiResponse response = gson.fromJson(content.toString(), ApiResponse.class);
    CityBuilder builder = new CityBuilder();
    City city = builder.setName(response.city.name)
        .setLon(response.city.coord.lon)
        .setLat(response.city.coord.lat)
        .setTemperatures(response.list
            .stream()
            .map(x -> x.measurements.temp)
            .collect(Collectors.toList()))
        .setHumidity(response.list
            .stream()
            .map(x -> x.measurements.humidity)
            .collect(Collectors.toList()))
        .setWeatherCodes(response.list
            .stream()
            .map(x -> x.weather.get(0).id)
            .collect(Collectors.toList()))
        .createCity();
    return gson.toJson(city);
  }
}
