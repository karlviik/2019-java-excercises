package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This parking lot only accepts small cars (size 1).
 * Each parking slot only accepts one car.
 */
public class SmallCarParkingLot extends ParkingLot {

    private Car[][] map;
    private HashMap<Car, Integer[]> locations = new HashMap<>();
    private ArrayList<Integer[]> emptySlots = new ArrayList<>();

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height
     * @param width
     */
    public SmallCarParkingLot(int height, int width) {
        super(height, width);
        map = new Car[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                emptySlots.add(new Integer[] {i, j});
            }
        }
    }



    @Override
    public String getTable() {
        LinkedList<String> tableRows = new LinkedList<>();
        for (Car[] cars : map) {
            String row = "";
            String otherRow = "";
            for (Car car : cars) {
                if (car == null) {
                    row = row.concat("..");
                } else {
                    row = row.concat(car.toString());
                }
                otherRow = otherRow.concat("..");
            }
            tableRows.add(row);
            tableRows.add(otherRow);
        }
        return String.join("\n", tableRows);
    }

    @Override
    public void processQueue() {
        while (emptySlots.size() != 0 && queue.size() != 0) {
            Car car = queue.poll();
            Integer[] coords = emptySlots.get(0);
            emptySlots.remove(0);
            locations.put(car, coords);
            map[coords[0]][coords[1]] = car;
            parkedCars.add(car);
            car.setParked();
        }
    }

    @Override
    public boolean doYouAcceptThisCar(Car car) {
        return car.getSize() == 1;
    }

    @Override
    public void unparkCar(Car car) {
        Integer[] coords = locations.get(car);
        locations.remove(car);
        map[coords[0]][coords[1]] = null;
        emptySlots.add(coords);
        parkedCars.remove(car);
        processQueue();
    }
}
