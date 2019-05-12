package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Desk;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class DeskBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;

    public DeskBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public DeskBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public DeskBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public DeskBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public DeskBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public DeskBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public Desk createDesk() {
        return new Desk(model, height, width, length, price, requiredMaterials);
    }
}