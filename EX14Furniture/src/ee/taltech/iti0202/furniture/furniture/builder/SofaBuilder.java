package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Sofa;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class SofaBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;
    private int sittingSpots;

    public SofaBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public SofaBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public SofaBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public SofaBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public SofaBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public SofaBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public SofaBuilder setSittingSpots(int sittingSpots) {
        this.sittingSpots = sittingSpots;
        return this;
    }

    public Sofa createSofa() {
        return new Sofa(model, height, width, length, price, requiredMaterials, sittingSpots);
    }
}