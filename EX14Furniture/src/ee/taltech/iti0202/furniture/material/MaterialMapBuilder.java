package ee.taltech.iti0202.furniture.material;

import java.util.HashMap;

/**
 * Builder for easily building material maps, used in furniture creation and warehouse.
 */
public class MaterialMapBuilder {
    private HashMap<Material, Float> materialMap = new HashMap<>();

    /**
     * If exists already, overwrites.
     */
    public MaterialMapBuilder addMaterial(Material material, Float amount) {
        materialMap.put(material, amount);
        return this;
    }

    public HashMap<Material, Float> buildMaterialMap() {
        return materialMap;
    }
}
