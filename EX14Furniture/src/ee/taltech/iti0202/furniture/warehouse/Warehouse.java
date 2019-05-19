package ee.taltech.iti0202.furniture.warehouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.furniture.catalogue.Catalogue;
import ee.taltech.iti0202.furniture.furniture.Furniture;
import ee.taltech.iti0202.furniture.material.Material;
import ee.taltech.iti0202.furniture.warehouse.filter.FurnitureFilter;
import ee.taltech.iti0202.furniture.warehouse.format.FurnitureBuild;
import ee.taltech.iti0202.furniture.warehouse.format.FurnitureStock;
import ee.taltech.iti0202.furniture.warehouse.format.MaterialStock;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Warehouse class.
 * FurnitureStocks hashmap includes all furniture the establishment has the plans for.
 * Everything in there is also in the catalogue.
 */
public class Warehouse {

    private Catalogue catalogue;
    private ArrayList<FurnitureStock> furnitureStocks;
    private ArrayList<MaterialStock> materialStocks;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Warehouse() {
        this(new ArrayList<>());
    }

    public Warehouse(ArrayList<FurnitureStock> furnitureStocks) {
        this.furnitureStocks = furnitureStocks;
        this.catalogue = new Catalogue();
        for(FurnitureStock furnitureStock : furnitureStocks) {
            catalogue.addFurniture(furnitureStock.getFurniture().getCatalogueDisplay());
        }
        this.materialStocks = new ArrayList<>();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public String getFurnitureStocksJSON(FurnitureFilter filter) {
        return gson.toJson(furnitureStocks.stream()
            .filter(x -> filter.getInStock().isEmpty() || !filter.getInStock().get() || !x.getStock().equals(0))
            .filter(x -> filter.getType().isEmpty() || filter.getType().get().equalsIgnoreCase(x.getFurniture().getType()))
            .filter(x -> filter.getModel().isEmpty() || filter.getModel().get().equalsIgnoreCase(x.getFurniture().getModel()))
            .filter(x -> filter.getMinPrice().isEmpty() || filter.getMinPrice().get() <= x.getFurniture().getPrice())
            .filter(x -> filter.getMaxPrice().isEmpty() || filter.getMaxPrice().get() >= x.getFurniture().getPrice())
            .collect(Collectors.toList()));
    }

    public String getMaterialStocksJSON() {
        return getMaterialStocksJSON(null);
    }

    public String getMaterialStocksJSON(Material material) {
        return gson.toJson(materialStocks.stream().filter(x -> material == null || x.getMaterial().equals(material)));
    }

    public boolean changeMaterialStock(Material material, Float amount) {
        MaterialStock stockObject = materialStocks.stream().filter(x -> x.getMaterial().equals(material)).findFirst().orElse(null);
        if (stockObject == null) {
            stockObject = new MaterialStock(material, 0f);
            materialStocks.add(stockObject);
        }
        if (stockObject.getStock() + amount >= 0) {
            stockObject.changeStock(amount);
            return true;
        }
        return false;
    }

    public void changeFurnitureStock(Furniture furniture, Integer stock) {
        if (furnitureStocks.stream().filter(x -> x.getFurniture().equals(furniture)).findFirst().isEmpty()) {
            furniture.getRequiredMaterials().keySet().forEach(x -> changeMaterialStock(x, 0f));
            furnitureStocks.add(new FurnitureStock(furniture, stock));
            catalogue.addFurniture(furniture.getCatalogueDisplay());
            return;
        }
        furnitureStocks.stream().filter(x -> x.getFurniture().equals(furniture)).findFirst().get().changeStock(stock);
    }

    private Integer getMaxBuildAmount(Furniture furniture) {
        return materialStocks.stream()
            .mapToInt(x -> (int) Math.floor(x.getStock() / furniture.getRequiredMaterials().get(x.getMaterial())))
            .map(x -> x < 0 ? 0 : x)
            .min()
            .orElse(0);
    }

    public String getMaterialsRequiredForFurniture(Furniture furniture, Integer amount) {
        return gson.toJson(furniture.getRequiredMaterials().entrySet().stream()
            .map(x -> new MaterialStock(x.getKey(), x.getValue() * amount - materialStocks.stream()
                .filter(y -> y.getMaterial().equals(x.getKey()))
                .findFirst()
                .get()
                .getStock()))
            .filter(x -> x.getStock() > 0)
            .collect(Collectors.toList()));
    }

    public String getMaxAmountPossibleToBuild(Furniture furniture) {
        return gson.toJson(new FurnitureBuild(furniture, getMaxBuildAmount(furniture)));
    }

    public boolean build(Furniture furniture, Integer amount) {
        if (getMaxBuildAmount(furniture) >= amount) {
            furniture.getRequiredMaterials().forEach((key, value) -> {
                materialStocks.stream().filter(y -> y.getMaterial().equals(key)).findFirst().get().changeStock(-value * amount);
                changeFurnitureStock(furniture, amount);
            });
            return true;
        }
        return false;
    }
}
