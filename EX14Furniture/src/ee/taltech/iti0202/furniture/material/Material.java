package ee.taltech.iti0202.furniture.material;

/**
 * Enum for material types, all used types go here with the displayed name as custom String value.
 */
public enum Material {

    GLASS("Glass"),
    WALNUT("Walnut"),
    RED_OAK("Red oak"),
    CHERRY("Cherry"),
    SPRUCE("Spruce"),
    PLYWOOD("Plywood"),
    CHIPBOARD("Chipboard"),
    IRON_ROD("Iron rod"),
    ALUMINIUM_SHEET("Aluminium sheet"),
    CAR_TYRE("Car tyre"),
    GOAT_BLOOD("Goat blood");

    private String custom;

    Material(String custom) {
        this.custom = custom;
    }

    @Override
    public String toString() {
        return custom;
    }
}
