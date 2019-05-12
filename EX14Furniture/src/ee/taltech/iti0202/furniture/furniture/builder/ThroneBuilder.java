package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Throne;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class ThroneBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;
    private Integer badassness;

    public ThroneBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ThroneBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public ThroneBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public ThroneBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public ThroneBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ThroneBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public ThroneBuilder setBadassness(Integer badassness) {
        this.badassness = badassness;
        return this;
    }

    public Throne createThrone() {
        return new Throne(model, height, width, length, price, requiredMaterials, badassness);
    }
}