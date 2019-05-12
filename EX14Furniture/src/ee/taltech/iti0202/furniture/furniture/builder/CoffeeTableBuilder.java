package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.CoffeeTable;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class CoffeeTableBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;

    public CoffeeTableBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CoffeeTableBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public CoffeeTableBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public CoffeeTableBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public CoffeeTableBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public CoffeeTableBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public CoffeeTable createCoffeeTable() {
        return new CoffeeTable(model, height, width, length, price, requiredMaterials);
    }
}