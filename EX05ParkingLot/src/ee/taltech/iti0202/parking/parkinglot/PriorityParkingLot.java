package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Accepts all cars if the queue size is less than 5.
 * Small car (size 1) with the highest priority can park alone.
 * Otherwise small cars (size 1) can share a slot if they have the same priority.
 * If there are cars with highest priority in the queue, then cars with common priority (if parked)
 * will be sent to the queue to make room for highest priority cars (life is unfair).
 *
 */
public class PriorityParkingLot extends ParkingLot {

    private Car[][][] map;
    private HashMap<Car, ArrayList<Integer[]>> locations = new HashMap<>();
    // queue
    // parkedcars

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of vertical side.
     * @param width  Length of horizontal side.
     */
    public PriorityParkingLot(int height, int width) {
        super(height, width);
        map = new Car[height][width][2];
    }

    @Override
    public void processQueue() {
        while (queue.size() > 0) {
            Car car = queue.peek();
            if (car.getPriorityStatus() == Car.PriorityStatus.HIGHEST) {
                unparkAndQueueAllCommonCars();
                continue;
            }
            ArrayList<Integer[]> coords = getParkingSpot(car.getSize(), car.getPriorityStatus());
            if (coords.size() == 0) {
                return;
//                if (car.getPriorityStatus() == Car.PriorityStatus.HIGHEST) {
//                    return;
//                } else {
//                    if (unparkAndQueueAllCommonCars()) {
//                        continue;
//                    }
//                    return;
//                }
            }
            parkedCars.add(queue.poll());
            locations.put(car, coords);
            for (Integer[] coord : coords) {
                map[coord[0]][coord[1]][coord[2]] = car;
            }

        }
    }

    private ArrayList<Integer[]> getParkingSpot(Integer size, Car.PriorityStatus status) {
        ArrayList<Integer[]> parkingSpots = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (size == 1 && status != Car.PriorityStatus.HIGHEST) {
                    for (int k = 0; k < 2; k++) {
                        if (map[i][j][k] == null) {
                            if (map[i][j][(k + 1) % 2] != null) {
                                if (map[i][j][(k + 1) % 2].getPriorityStatus() == status) {
                                    parkingSpots.add(new Integer[]{i, j, k});
                                    return parkingSpots;
                                }
                            } else {
                                parkingSpots.add(new Integer[]{i, j, k});
                                return parkingSpots;
                            }
                        }
                    }
                } else if (size == 2 || size == 1) {
                    if (map[i][j][0] == null && map[i][j][1] == null) {
                        parkingSpots.add(new Integer[] {i, j, 0});
                        if (size != 1) {
                            parkingSpots.add(new Integer[] {i, j, 1});
                        }
                        return parkingSpots;
                    }
                } else {
                    if (map[i][j][0] == null && map[i][j][1] == null) {
                        if (i < height - 1 && map[i + 1][j][0] == null && map[i + 1][j][1] == null) {
                            parkingSpots.add(new Integer[] {i, j, 0});
                            parkingSpots.add(new Integer[] {i, j, 1});
                            parkingSpots.add(new Integer[] {i + 1, j, 0});
                            parkingSpots.add(new Integer[] {i + 1, j, 1});
                            return parkingSpots;
                        } else if (j < width - 1 && map[i][j + 1][0] == null && map[i][j + 1][1] == null) {
                            parkingSpots.add(new Integer[] {i, j, 0});
                            parkingSpots.add(new Integer[] {i, j, 1});
                            parkingSpots.add(new Integer[] {i, j + 1, 0});
                            parkingSpots.add(new Integer[] {i, j + 1, 1});
                            return parkingSpots;
                        }
                    }
                }
            }
        }
        return parkingSpots;
    }

    private boolean unparkAndQueueAllCommonCars() {
        List<Car> parkedCommonCars = parkedCars.stream()
                .filter(x -> x.getPriorityStatus() == Car.PriorityStatus.COMMON)
                .collect(Collectors.toList());
        if (parkedCommonCars.size() == 0) {
            return false;
        }
        for (Car car : parkedCommonCars) {
            removeFromLists(car);
            queue.add(car);
            car.setUnparked();
        }
        return true;
    }

    @Override
    public String getTable() {
        LinkedList<String> tableRows = new LinkedList<>();
        for (Car[][] parkingSpotRow : map) {
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
        return String.join("\n", tableRows);
    }

    @Override
    public boolean doYouAcceptThisCar(Car car) {
        return queue.size() < 5;
    }

    @Override
    public void unparkCar(Car car) {
        removeFromLists(car);
        processQueue();
    }

    private void removeFromLists(Car car) {
        if (!car.isParked()) {
            queue.remove(car);
        } else {
            ArrayList<Integer[]> coords = locations.get(car);
            locations.remove(car);
            for (Integer[] coord : coords) {
                map[coord[0]][coord[1]][coord[2]] = null;
            }
            parkedCars.remove(car);
        }
    }
}
