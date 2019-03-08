package ee.taltech.iti0202.parking;
import ee.taltech.iti0202.parking.car.Car;
import ee.taltech.iti0202.parking.parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Comparator;

public class City {
    private String name;
    private ArrayList<ParkingLot> parkingLots;
    private int creationNo = 0;

    public City(String name) {
        this.name = name;
    }

    /**
     * Adds a parking lot.
     * A parking lot can exist only once.
     * @param parkingLot Parking lot to be added.
     * @return true if parking lot was added.
     */
    public boolean addParkingLot(ParkingLot parkingLot) {
        if (parkingLots.contains(parkingLot)) {
            return false;
        }
        parkingLot.setCreationId(creationNo);
        creationNo += 1;
        parkingLots.add(parkingLot);
        return true;
    }

    /**
     * Tries to send a car to a parking lot.
     * If the parking lot accepts this car
     * the car will be added to the queue of the parking lot.
     * The chosen parking lot is returned.
     * If several parking lots can take the car, use the one
     * with the smallest queue.
     * If several have the same size queue, use the one
     * which was added earlier.
     * Or empty in case the car cannot be parked anywhere
     * or the car has already been parked or is in queue.
     * @param car Car to be sent to parking lot
     * @return Parking lot where the car will be sent into queue.
     *          empty() in case no parking lot is suitable.
     */
    public Optional<ParkingLot> parkCar(Car car) {
        ArrayList<ParkingLot> viableLots = new ArrayList<>();
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getQueue().contains(car) || parkingLot.getParkedCars().contains(car)) {
                return Optional.empty();
            }
            if (parkingLot.doYouAcceptThisCar(car)) {
                viableLots.add(parkingLot);
            }
        }
        if (viableLots.size() == 0) {
            return Optional.empty();
        }
        ParkingLot targetLot = viableLots.stream()
                .filter(x-> x.getQueueSize() == viableLots.stream()
                        .min(Comparator.comparingInt(ParkingLot::getQueueSize))
                        .get()
                        .getQueueSize())
                .min(Comparator.comparing(ParkingLot::getCreationId))
                .get();
        targetLot.addToQueue(car);
        return Optional.of(targetLot);
    }

    /**
     * Gets all parking lots in a city.
     * @return List of parking lots.
     */
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    /**
     * Return a map where for every priority-size pair a count of cars is mapped.
     * Keys are in format XY
     * where X = {H, P, C} (highest, priority, common)
     * Y = {1, 2, 4} size
     * @return map with priority-size counts
     */
    public Map<String, Integer> getParkedCarCountBySizeAndPriority() {
        HashMap<String, Integer> parkedCarsMap = new HashMap<>();
        for (String carString : Car.POSSIBLE_STRINGS) {
            parkedCarsMap.put(carString, 0);
        }
        for (ParkingLot parkingLot : parkingLots) {
            for (Car car : parkingLot.getParkedCars()) {
                parkedCarsMap.put(car.toString(), parkedCarsMap.get(car.toString()) + 1);
            }
        }
        return parkedCarsMap;
    }

    /**
     * Gets car count in queue by priority status and size.
     * @param priorityStatus (highest, priority, common)
     * @param size (1, 2, 4)
     * @return Count of cars in queue.
     */
    public int getCarCountInQueue(Car.PriorityStatus priorityStatus, int size) {
        int queuedCarsCount = 0;
        for (ParkingLot parkingLot : parkingLots) {
            for (Car car : parkingLot.getQueue()) {
                if (car.getPriorityStatus() == priorityStatus) {
                    queuedCarsCount++;
                }
            }
        }
        return queuedCarsCount;
    }

    /**
     * Gets parked car count by priority status and size.
     * @param priorityStatus (highest, priority, common)
     * @param size (1, 2, 4)
     * @return Count of parked cars.
     */
    public int getParkedCarCount(Car.PriorityStatus priorityStatus, int size) {
        int parkedCarCount = 0;
        for (ParkingLot parkingLot : parkingLots) {
            for (Car car : parkingLot.getParkedCars()) {
                if (car.getPriorityStatus() == priorityStatus) {
                    parkedCarCount++;
                }
            }
        }
        return parkedCarCount;
    }

    public String getName() {
        return name;
    }
}
