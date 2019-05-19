package ee.taltech.iti0202.furniture.furniture;

import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Chair extends Furniture {

    private int legs;
    private int comfiness;

    public Chair(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials, int legs, int comfiness) {
        super("chair", model, height, width, length, price, requiredMaterials);
        this.legs = legs;
        this.comfiness = comfiness;
    }

    public int getLegs() {
        return legs;
    }

    public int getComfiness() {
        return comfiness;
    }
}
