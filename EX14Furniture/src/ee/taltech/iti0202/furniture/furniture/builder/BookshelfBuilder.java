package ee.taltech.iti0202.furniture.furniture.builder;

import ee.taltech.iti0202.furniture.furniture.Bookshelf;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class BookshelfBuilder {
    private String model;
    private float height;
    private float width;
    private float length;
    private float price;
    private HashMap<Material, Float> requiredMaterials;
    private int shelves;
    private int drawers;

    public BookshelfBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public BookshelfBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public BookshelfBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public BookshelfBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public BookshelfBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public BookshelfBuilder setRequiredMaterials(HashMap<Material, Float> requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
        return this;
    }

    public BookshelfBuilder setShelves(int shelves) {
        this.shelves = shelves;
        return this;
    }

    public BookshelfBuilder setDrawers(int drawers) {
        this.drawers = drawers;
        return this;
    }

    public Bookshelf createBookshelf() {
        return new Bookshelf(model, height, width, length, price, requiredMaterials, shelves, drawers);
    }
}