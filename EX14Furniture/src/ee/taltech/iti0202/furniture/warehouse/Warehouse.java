package ee.taltech.iti0202.furniture.warehouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.furniture.catalogue.Catalogue;
import ee.taltech.iti0202.furniture.furniture.Furniture;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Warehouse class.
 * FurnitureStocks hashmap includes all furniture the establishment has the plans for.
 * Everything in there is also in the catalogue.
 */
public class Warehouse {

    private Catalogue catalogue;
    private HashMap<Furniture, Integer> furnitureStocks;
    private HashMap<Material, Float> materialStocks;
    private WarehouseFurnitureBuilder builder;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Warehouse() throws CloneNotSupportedException {
        this(new HashMap<>());
    }

    public Warehouse(HashMap<Furniture, Integer> furnitureStocks) throws CloneNotSupportedException {
        this.furnitureStocks = furnitureStocks;
        this.catalogue = new Catalogue();
        for(Furniture furniture : furnitureStocks.keySet()) {
            catalogue.addFurniture(furniture.getCatalogueDisplay());
        }
        this.builder = new WarehouseFurnitureBuilder(this);
        this.materialStocks = new HashMap<>();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public HashMap<Furniture, Integer> getAllFurnitureStocks() {
        return furnitureStocks;
    }

    private List<Map.Entry<Furniture, Integer>> getFurnitureStocksEntryList() {
        return new ArrayList<>(furnitureStocks.entrySet());
    }

    private List<Map.Entry<Furniture, Integer>> filterOutNotInStock(Stream<Map.Entry<Furniture, Integer>> stream) {
        return stream.filter(x -> x.getValue() > 0).collect(Collectors.toList());
    }

    public String getFurnitureStocksJSON(boolean onlyInStock) {
        if (!onlyInStock) {
            return gson.toJson(this.getFurnitureStocksEntryList());
        }
        return gson.toJson(filterOutNotInStock(this.getFurnitureStocksEntryList().stream()));
    }

    public String getFurnitureStockOfTypeJSON(boolean onlyInStock, String type) {
        Stream<Map.Entry<Furniture, Integer>> filteredStream = this.getFurnitureStocksEntryList()
                .stream()
                .filter(x -> x.getKey().getType().equals(type));
        if (onlyInStock) {
            return gson.toJson(filteredStream.collect(Collectors.toList()));
        }
        return gson.toJson(filterOutNotInStock(filteredStream));
    }

    public String getFurnitureStockOfModelJSON(boolean onlyInStock, String model) {
        Stream<Map.Entry<Furniture, Integer>> filteredStream = this.getFurnitureStocksEntryList()
                .stream()
                .filter(x -> x.getKey().getModel().equals(model));
        if (onlyInStock) {
            return gson.toJson(filteredStream.collect(Collectors.toList()));
        }
        return gson.toJson(filterOutNotInStock(filteredStream));
    }

    public String getFurnitureStockOfPriceBetweenGivenJSON(boolean onlyInStock, Float lowPrice, Float highPrice) {
        Stream<Map.Entry<Furniture, Integer>> filteredStream = this.getFurnitureStocksEntryList()
                .stream()
                .filter(x -> lowPrice <= x.getKey().getPrice() && x.getKey().getPrice() <= highPrice);
        if (onlyInStock) {
            return gson.toJson(filteredStream.collect(Collectors.toList()));
        }
        return gson.toJson(filterOutNotInStock(filteredStream));
    }

    public String getFurnitureObjectsJSON(boolean onlyInStock) {
        Stream<Map.Entry<Furniture, Integer>> stream = getFurnitureStocksEntryList().stream();
        if (onlyInStock) {
            return gson.toJson(stream
                    .filter(x -> x.getValue() > 0)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()));
        }
        return gson.toJson(stream
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }

    public HashMap<Material, Float> getMaterialStocks() {
        return materialStocks;
    }

    public String getMaterialStocksJSON() {
        return gson.toJson(new ArrayList<>(materialStocks.entrySet()));
    }

    public String getSpecificMaterialStocksJSON(Material material) {
        return gson.toJson(new ArrayList<>(materialStocks.entrySet())
                .stream()
                .filter(x -> x.getKey().equals(material)));
    }

    public void addToMaterialStock(Material material, Float amount) {
        if (amount < 0) {
            return;
        }
        materialStocks.put(material, materialStocks.getOrDefault(material, 0f) + amount);
    }

    public void removeFromMaterialStock(Material material, Float amount) {
        if (amount > 0 || !materialStocks.containsKey(material) || materialStocks.get(material) < amount) {
            return;
        }
        materialStocks.put(material, materialStocks.get(material) - amount);
    }

    public void addFurnitureStock(Furniture furniture, Integer stock) throws CloneNotSupportedException {
        if(!furnitureStocks.containsKey(furniture)) {
          catalogue.addFurniture(furniture.getCatalogueDisplay());
          for(Material material : furniture.getRequiredMaterials().keySet()) {
              materialStocks.put(material, materialStocks.getOrDefault(material, 0f));
          }
        }
        furnitureStocks.put(furniture, furnitureStocks.getOrDefault(furniture, 0) + stock);
    }

    public void removeFurniture(Furniture furniture) {
        furnitureStocks.remove(furniture);
        catalogue.removeFurniture(furniture);
    }

    public Float getMaterialStockOf(Material material) {
        return materialStocks.get(material);
    }

    public String getMaterialsRequiredForFurniture(Furniture furniture, Integer amount) {
        return gson.toJson(new AbstractMap.SimpleEntry<>(furniture, builder.whatMoreMaterialsRequiredByObject(furniture, amount)));
    }

    public String getMaxAmountPossibleToBuild(Furniture furniture) {
        return gson.toJson(new AbstractMap.SimpleEntry<>(furniture, builder.howManyCanBuild(furniture)));
    }

    public String build(Furniture furniture, Integer amount) throws CloneNotSupportedException {
        return gson.toJson(new AbstractMap.SimpleEntry<>(furniture, builder.build(furniture, amount)));
    }
}
