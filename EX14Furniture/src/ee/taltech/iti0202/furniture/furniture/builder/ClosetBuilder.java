package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Closet;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class ClosetBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;
    private float howManyPeopleCanFitIn;

    public ClosetBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ClosetBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public ClosetBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public ClosetBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public ClosetBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ClosetBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public ClosetBuilder setHowManyPeopleCanFitIn(float howManyPeopleCanFitIn) {
        this.howManyPeopleCanFitIn = howManyPeopleCanFitIn;
        return this;
    }

    public Closet createCloset() {
        return new Closet(model, height, width, length, price, requiredMaterials, howManyPeopleCanFitIn);
    }
}