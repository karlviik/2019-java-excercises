package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.agency.ChoosingClient;
import ee.taltech.iti0202.api.agency.Client;
import ee.taltech.iti0202.api.agency.TravelAgency;
import ee.taltech.iti0202.api.provider.OnlineDataController;
import ee.taltech.iti0202.api.strategies.LovesChangeCityFinder;
import ee.taltech.iti0202.api.strategies.WarmWeatherCityFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    List<String> cities = new ArrayList<>();
    cities.add("Lloret de Mar");
    Client client = new Client("peeter", "London", new LovesChangeCityFinder());
    cities.add("Londonnnnnnn");
    cities.add("Amsterdam");

    cities.add("Oslo");

    cities.add("Helsinki");
    cities.add("Riga");
    cities.add("Kuressaare");

    TravelAgency agency = new TravelAgency(cities, new OnlineDataController());
    System.out.println(agency.findSuitableCitiesForClient(client).get().getName());
  }
}
