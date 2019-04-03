package ee.taltech.iti0202.kt1.port;

public class Cargo {
    private String name;
    private int weight;
    private int size;

    public Cargo(String name, int weight, int size) {
        this.name = name;
        this.weight = weight;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }

    public int getTotalWeight() {
        if (size < 10 || weight < 10) {
            return 10;
        }
        return size;
    }

    public String toString() {
        return String.format("Name: %s, Total: %s", name, getTotalWeight());
    }
}
