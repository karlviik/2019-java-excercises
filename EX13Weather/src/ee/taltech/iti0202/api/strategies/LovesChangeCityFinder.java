package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

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
    return Optional.empty();
  }
}
