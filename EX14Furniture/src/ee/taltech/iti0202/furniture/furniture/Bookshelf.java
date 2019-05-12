package ee.taltech.iti0202.furniture.furniture;

import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Bookshelf extends Furniture {

    int shelves;
    int drawers;

    public Bookshelf(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials, int shelves, int drawers) {
        super("bookshelf", model, height, width, length, price, requiredMaterials);
        this.shelves = shelves;
        this.drawers = drawers;
    }

    public int getShelves() {
        return shelves;
    }

    public int getDrawers() {
        return drawers;
    }
}
