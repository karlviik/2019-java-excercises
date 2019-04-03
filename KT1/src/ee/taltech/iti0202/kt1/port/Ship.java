package ee.taltech.iti0202.kt1.port;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private List<String> restrictions;
    private int capacity;
    private int currentCapacity = 0;
    private ArrayList<Cargo> cargoList = new ArrayList<>();

    public Ship(String name, List<String> restrictions, int capacity) {
        this.name = name;
        this.restrictions = restrictions;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public boolean addCargo(Cargo cargo) {
        if (cargo == null
                || cargoList.contains(cargo)
                || restrictions.contains(cargo.getName())
                || currentCapacity + cargo.getTotalWeight() > capacity) {
            return false;
        }
        cargoList.add(cargo);
        currentCapacity += cargo.getTotalWeight();
        return true;
    }

    public int getCurrentCapacityPercentage() {
        return 100 * currentCapacity / capacity;
    }

    public void addRestriction(String restriction) {
        if (restrictions.contains(restriction)) {
            restrictions.remove(restriction);
        } else {
            restrictions.add(restriction);
        }
    }
    public List<Cargo> getCargoList() {
        return cargoList;
    }
}
