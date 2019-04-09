package ee.taltech.iti0202.computershop.component;

public abstract class Component {
    private int powerConsumption;
    private String name;
    private String description;
    private double price;
    private int performacePoints;
    ComponentType componentType;
    public enum ComponentType {
        PSU,
        MoBo,
        GPU,
        RAM,
        CPU,
        Storage
    }

    public Component(int powerConsumption, String name, String description, double price, int performacePoints) {
        this.powerConsumption = powerConsumption;
        this.name = name;
        this.description = description;
        this.price = price;
        this.performacePoints = performacePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getPerformacePoints() {
        return performacePoints;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, description);
    }
}
