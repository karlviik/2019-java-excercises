package ee.taltech.iti0202.furniture.furniture;

import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Sofa extends Furniture {

    @SerializedName("sitting spots")
    int sittingSpots;

    public Sofa(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials, int sittingSpots) {
        super("sofa", model, height, width, length, price, requiredMaterials);
        this.sittingSpots = sittingSpots;
    }

    public int getSittingSpots() {
        return sittingSpots;
    }
}
