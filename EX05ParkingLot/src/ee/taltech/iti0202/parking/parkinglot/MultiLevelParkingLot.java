
package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Modern parking lot located under ground.
 * The parking lot has several levels.
 * Always prefer the smallest level (starting from 1).
 * If the car cannot fit to a level, then proceed to the next level.
 *
 * This parking lot only accepts maximum 10 vehicle in the queue.
 * So, if there queue already has 10 cars, this parking lot should not
 * be available for new cars.
 *
 */
public class MultiLevelParkingLot extends ParkingLot {

    private final int levels;
    private Car[][][][] map;
    private HashMap<Car, ArrayList<Integer[]>> locations = new HashMap<>();
    // queue
    // parkedcars

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of verical side.
     * @param width  Length of horizontal side.
     * @param levels Number of levels.
     */
    public MultiLevelParkingLot(int height, int width, int levels) {
        super(height, width);
        this.levels = levels;
        map = new Car[levels][height][width][2];
    }

    @Override
    public void processQueue() {
        while (queue.size() > 0) {
            Car car = queue.peek();
            ArrayList<Integer[]> coords = getParkingSpot(car.getSize());
            if (coords.size() == 0) {
                return;
            }
            parkedCars.add(queue.poll());
            locations.put(car, coords);
            car.setParked();
            for (Integer[] coord : coords) {
                map[coord[0]][coord[1]][coord[2]][coord[3]] = car;
            }

        }
    }

    private ArrayList<Integer[]> getParkingSpot(Integer size) {
        ArrayList<Integer[]> parkingSpots = new ArrayList<>();
        for (int i = 0; i < levels; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    if (size == 1) {
                        for (int l = 0; l < 2; l++) {
                            if (map[i][j][k][l] == null) {
                                parkingSpots.add(new Integer[]{i, j, k, l});
                                return parkingSpots;
                            }
                        }
                    } else if (size == 2) {
                        if (map[i][j][k][0] == null && map[i][j][k][1] == null) {
                            parkingSpots.add(new Integer[]{i, j, k, 0});
                            parkingSpots.add(new Integer[]{i, j, k, 1});
                            return parkingSpots;
                        }
                    } else {
                        if (map[i][j][k][0] == null && map[i][j][k][1] == null) {
                            if (j < height - 1 && map[i][j + 1][k][0] == null && map[i][j + 1][k][1] == null) {
                                parkingSpots.add(new Integer[]{i, j, k, 0});
                                parkingSpots.add(new Integer[]{i, j, k, 1});
                                parkingSpots.add(new Integer[]{i + 1, j, k, 0});
                                parkingSpots.add(new Integer[]{i + 1, j, k, 1});
                                return parkingSpots;
                            } else if (k < width - 1 && map[i][j][k + 1][0] == null && map[i][j][k + 1][1] == null) {
                                parkingSpots.add(new Integer[]{i, j, k, 0});
                                parkingSpots.add(new Integer[]{i, j, k, 1});
                                parkingSpots.add(new Integer[]{i, j + 1, k, 0});
                                parkingSpots.add(new Integer[]{i, j + 1, k, 1});
                                return parkingSpots;
                            }
                        }
                    }
                }
            }
        }
        return parkingSpots;
    }

    /**
     * Here you have to override getTable() method.
     * The method gets a string for each level
     * separated by "---":
     *
     * P4P4..
     * P4P4..
     * ......
     * ......
     * ---
     * ......
     * ......
     * ......
     * ......
     *
     * This has 2 levels and there is a large (size 2) car on first level.
     *
     * @return String representation of multilevel parking lot
     */
    @Override
    public String getTable() {
        LinkedList<String> tableRows = new LinkedList<>();
        for (int k = 0; k < levels; k++) {
            Car[][][] parkingLevel = map[k];
            for (Car[][] parkingSpotRow : parkingLevel) {
                String[] addedRows = {"", ""};
                for (Car[] parkingSpot : parkingSpotRow) {
                    for (int i = 0; i < 2; i++) {
                        if (parkingSpot[i] == null) {
                            addedRows[i] = addedRows[i].concat("..");
                        } else {
                            addedRows[i] = addedRows[i].concat(parkingSpot[i].toString());
                        }
                    }
                }
                tableRows.add(addedRows[0]);
                tableRows.add(addedRows[1]);
            }
            if (k != levels - 1) {
                tableRows.add("---");
            }
        }
        return String.join("\n", tableRows);
    }

    @Override
    public boolean doYouAcceptThisCar(Car car) {
        return queue.size() < 10;
    }

    @Override
    public void unparkCar(Car car) {
        if (!car.isParked()) {
            queue.remove(car);
        } else {
            ArrayList<Integer[]> coords = locations.get(car);
            locations.remove(car);
            for (Integer[] coord : coords) {
                map[coord[0]][coord[1]][coord[2]][coord[3]] = null;
            }
            parkedCars.remove(car);
        }
        processQueue();
    }
}
