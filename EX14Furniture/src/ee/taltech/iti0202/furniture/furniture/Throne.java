package ee.taltech.iti0202.furniture.furniture;

import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Throne extends Furniture {

    private Integer badassness;

    public Throne(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials, Integer badassness) {
        super("throne", model, height, width, length, price, requiredMaterials);
        this.badassness = badassness;
    }

    public Integer getBadassness() {
        return badassness;
    }
}
