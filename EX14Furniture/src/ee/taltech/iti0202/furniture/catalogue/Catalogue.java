package ee.taltech.iti0202.furniture.catalogue;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import ee.taltech.iti0202.furniture.furniture.Furniture;

import java.util.ArrayList;

public class Catalogue {
    @SerializedName("furniture")
    private ArrayList<Furniture> allFurniture;

    public Catalogue() {
        allFurniture = new ArrayList<>();
    }

    /**
     * Objects here are added from warehouse.
     *
     * @param furniture furniture object to be added, must be without materials and already not in there.
     * @return true if was added, false if not
     */
    public boolean addFurniture(Furniture furniture) {
        if (!allFurniture.contains(furniture) && furniture.getRequiredMaterials() == null) {
            allFurniture.add(furniture);
            return true;
        }
        return false;
    }

    /**
     * Removes furniture from catalogue.
     *
     * @param furniture object to be removed
     * @return true if was removed, false if not.
     */
    public boolean removeFurniture(Furniture furniture) {
        if (allFurniture.contains(furniture)) {
            allFurniture.remove(furniture);
            return true;
        }
        return false;
    }

    public String getAllFurnitureJSON() {
        return new Gson().toJson(allFurniture);
    }

    public ArrayList<Furniture> getAllFurniture() {
        return allFurniture;
    }
}
