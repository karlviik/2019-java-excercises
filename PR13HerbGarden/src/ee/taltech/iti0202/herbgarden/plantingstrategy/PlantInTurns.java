package ee.taltech.iti0202.herbgarden.plantingstrategy;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlantInTurns implements PlantingStrategy {
  @Override
  public String[][] plantHerbs(int height, int width, Map<String, Integer> plants) {
    String[][] plantField = new String[height][width];
    List<Map.Entry<String, Integer>> plantEntries = plants.entrySet().stream().sorted(Comparator.comparing(x -> -x.getValue())).collect(Collectors.toList());
    for (int currentHeight = 0; currentHeight < height; currentHeight++) {
      for (int currentWidth = 0; currentWidth < width; currentWidth++) {
        String plant = plantEntries.get(0).getKey();
        plantField[currentHeight][currentWidth] = plant;
        int newAmount = plantEntries.get(0).getValue() - 1;
        plantEntries.remove(0);
        if (newAmount != 0) {
          plantEntries.add(new AbstractMap.SimpleEntry<>(plant, newAmount));
        }
      }
    }
    return plantField;
  }
}
