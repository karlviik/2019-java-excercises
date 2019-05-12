package ee.taltech.iti0202.furniture.furniture;

import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Desk extends Furniture {

    public Desk(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials) {
        super("desk", model, height, width, length, price, requiredMaterials);
    }
}
