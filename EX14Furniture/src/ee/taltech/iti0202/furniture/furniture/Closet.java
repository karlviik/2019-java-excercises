package ee.taltech.iti0202.furniture.furniture;

import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.furniture.material.Material;

import java.util.HashMap;

public class Closet extends Furniture {

    @SerializedName("how many people can fit in")
    float howManyPeopleCanFitIn;

    public Closet(String model, float height, float width, float length, float price, HashMap<Material, Float> requiredMaterials, float howManyPeopleCanFitIn) {
        super("closet", model, height, width, length, price, requiredMaterials);
        this.howManyPeopleCanFitIn = howManyPeopleCanFitIn;
    }

    public float getHowManyPeopleCanFitIn() {
        return howManyPeopleCanFitIn;
    }

}
