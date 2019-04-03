package ee.taltech.iti0202.kt1.port;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Port {
    private List<Ship> ships;
    private String name;
    private List<Cargo> cargoStorage = new ArrayList<>();

    public Port(String name, List<Ship> ships) {
        this.ships = ships;
        this.name = name;
    }

    public Port(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        if (!ships.contains(ship)) {
            ships.add(ship);
        }
    }

    public List<Cargo> getCargoStorage() {
        return cargoStorage;
    }

    public void addCargo(List<Cargo> cargoList) {
        for (Cargo cargo : cargoList) {
            if (!cargoStorage.contains(cargo)) {
                cargoStorage.add(cargo);
            }
        }
    }

    public int emptyCargoStorage() {
        int successCount = 0;
        List<Cargo> toRemoveCargo = new ArrayList<>();
        for (Cargo cargo : cargoStorage) {
            for (Ship ship : ships.stream()
                    .sorted(Comparator.comparingInt(Ship::getCurrentCapacityPercentage))
                    .collect(Collectors.toList())) {
                if (ship.addCargo(cargo)) {
                    successCount++;
                    toRemoveCargo.add(cargo);
                    break;
                }
            }
        }
        cargoStorage.removeAll(toRemoveCargo);
        return successCount;
    }
}
