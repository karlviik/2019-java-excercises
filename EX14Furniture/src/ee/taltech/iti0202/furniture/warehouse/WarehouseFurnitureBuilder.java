package ee.taltech.iti0202.furniture.warehouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.furniture.furniture.Furniture;
import ee.taltech.iti0202.furniture.material.Material;
import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class WarehouseFurnitureBuilder {

    private final Warehouse warehouse;

    WarehouseFurnitureBuilder(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    List<Map.Entry<Material, Float>> whatTotalMaterialsRequiredByObject(Furniture furniture, Integer amount) {
        return new ArrayList<>(furniture
                .getRequiredMaterials()
                .entrySet()).stream()
                .map(x -> new SimpleEntry<>(x.getKey(), x.getValue() * amount))
                .collect(Collectors.toList());
    }

    List<Map.Entry<Material, Float>> whatMoreMaterialsRequiredByObject(Furniture furniture, Integer amount) {
        return new ArrayList<>(furniture
                .getRequiredMaterials()
                .entrySet()).stream()
                .map(x -> new SimpleEntry<>(x.getKey(), x.getValue() * amount - warehouse.getMaterialStockOf(x.getKey())))
                .filter(x -> x.getValue() > 0)
                .collect(Collectors.toList());
    }

    Integer howManyCanBuild(Furniture furniture) {
        float bestAmount = 0;
        for (Map.Entry<Material, Float> entry : furniture.getRequiredMaterials().entrySet()) {
            float thisAmount = warehouse.getMaterialStockOf(entry.getKey()) / entry.getValue();
            if (thisAmount > bestAmount) {
                bestAmount = thisAmount;
            }
        }
        return (int) bestAmount;
    }

    String build(Furniture furniture, Integer amount) throws CloneNotSupportedException {
        if (whatMoreMaterialsRequiredByObject(furniture, amount).size() == 0) {
            for (Map.Entry<Material, Float> entry : furniture.getRequiredMaterials().entrySet()) {
                warehouse.removeFromMaterialStock(entry.getKey(), entry.getValue() * amount);
            }
            warehouse.addFurnitureStock(furniture, amount);
            return "success";
        }
        return "failure";
    }
}
