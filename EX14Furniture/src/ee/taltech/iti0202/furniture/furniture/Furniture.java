package ee.taltech.iti0202.furniture.furniture;

import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adding new furniture types:
 * make a new class which extends this class with its own fields if there are any
 * and for ease of usage make a builder.
 *
 * Could have made an "overarching" builder which would have called a builder for any specified type,
 * but that would be a hassle to extend constantly with new additions.
 */
public abstract class Furniture implements Cloneable {

    protected final String type;
    protected final String model;
    protected final float height;
    protected final float width;
    protected final float length;
    protected final float price;
    @SerializedName("used_materials")
    protected final List<String> usedMaterials;
    @SerializedName("required_materials")
    protected HashMap<Material, Float> requiredMaterials;

    protected Furniture(
            String type,
            String model,
            float height,
            float width,
            float length,
            float price,
            HashMap<Material, Float> requiredMaterials
    ) {
        this.type = type;
        this.model = model;
        this.height = height;
        this.width = width;
        this.length = length;
        this.price = price;
        this.requiredMaterials = requiredMaterials;
        this.usedMaterials = new ArrayList<>(requiredMaterials.keySet())
                .stream()
                .map(Material::toString)
                .collect(Collectors.toList());
    }

    /**
     * Clones and changes the object so it would be fit for catalogue view.
     *
     * @return Furniture object which can be serialized to json without building material costs which are
     * irrelevant to the customer.
     */
    public Furniture getCatalogueDisplay() {
        Furniture clone;
        try {
            clone = (Furniture) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        clone.requiredMaterials = null;
        return clone;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    public float getPrice() {
        return price;
    }

    public List<String> getUsedMaterials() {
        return usedMaterials;
    }

    public HashMap<Material, Float> getRequiredMaterials() {
        return requiredMaterials;
    }

    @Override
    public String toString() {
        return "Type " + this.getType() + "; Model " + this.getModel();
    }
}
