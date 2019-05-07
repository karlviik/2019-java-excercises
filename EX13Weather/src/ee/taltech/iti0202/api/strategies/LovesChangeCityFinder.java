package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LovesChangeCityFinder implements CityFinderStrategy {
// TODO: this

  /**
   * Klient soovib, et ilm oleks võimalikult erinev iga päev, arvestada tuleb temperatuuri, niiskuse ning kliima muutust. Temperatuuri ning niiskuse kõikumist arvestage lihtsalt iga päeva keskmise väärtuse kõikumisega. Ilma koodide kõikumisel tuleb arvestada kolme asjaga:
   *
   * Kui ilmakood ei muutu, vähendage kaalu 10 võrra
   * Kui koodi esimene number ei muutu aga järgnevad muutuvad, suurendage kaalu 20 võrra
   * Kui koodi esimene number muutub, suurendage kaalu 100 võrra
   * Temperatuuri ning niiskust arvestage päeva kaupa, igas päeva kohta on json-is 8 väärtust. Ilmakoode arvestage iga kolme tunni kohta, ehk 40 väärtust.
   */
  @Override
  public Optional<City> findBestCity(List<City> candidateCities) {
    Double bestWeight = Double.MIN_VALUE;
    Optional<City> bestCity = Optional.empty();
    for (City city : candidateCities) {
      Double weight = 0d;
      int miniCounter = 0;
      Double tempSum = 0d;
      Double humidSum = 0d;
      List<Double> tempDiffs = new ArrayList<>();
      List<Double> humidDiffs = new ArrayList<>();
      List<Double> temps = city.getTemperatures();
      List<Double> humids = city.getHumidity();
      List<Integer> codes = city.getWeatherCodes();
      Integer lastCode = 0;
      Integer code;
      for (int i = 0; i < 40; i++) {
        tempSum += temps.get(i);
        humidSum += humids.get(i);
        miniCounter++;
        if (miniCounter == 8) {
          miniCounter = 0;
          tempDiffs.add(tempSum);
          humidDiffs.add(humidSum);
          humidSum = 0d;
          tempSum = 0d;
        }
        code = codes.get(i);
        if (i > 0) {
          if (code.equals(lastCode)) {
            weight -= 10;
          }
          else if (code / 100 != lastCode / 100) {
            weight += 100;
          }
          else {
            weight += 20;
          }
        }
        lastCode = code;
      }
      for (int i = 1; i < 5; i++) {
        weight += Math.abs(tempDiffs.get(i) - tempDiffs.get(i - 1));
        weight += Math.abs(humidDiffs.get(i) - humidDiffs.get(i - 1));
      }
      if (bestWeight < weight) {
        bestWeight = weight;
        bestCity = Optional.of(city);
      }
    }
    return bestCity;
  }
}
