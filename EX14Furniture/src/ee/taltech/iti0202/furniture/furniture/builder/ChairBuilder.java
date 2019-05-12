package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Chair;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class ChairBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;
    private int legs;
    private int comfiness;

    public ChairBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ChairBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public ChairBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public ChairBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public ChairBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ChairBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public ChairBuilder setLegs(int legs) {
        this.legs = legs;
        return this;
    }

    public ChairBuilder setComfiness(int comfiness) {
        this.comfiness = comfiness;
        return this;
    }

    public Chair createChair() {
        return new Chair(model, height, width, length, price, requiredMaterials, legs, comfiness);
    }
}