package ee.taltech.iti0202.furniture.furniture;

import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class CoffeeTable extends Furniture {

    public CoffeeTable(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials) {
        super("coffee table", model, height, width, length, price, requiredMaterials);
    }
}
