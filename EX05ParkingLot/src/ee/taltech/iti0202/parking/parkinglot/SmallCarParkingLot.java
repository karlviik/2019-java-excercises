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

    private Car[][][] map;
    private HashMap<Car, Integer[][]> locations;

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height
     * @param width
     */
    public SmallCarParkingLot(int height, int width) {
        super(height, width);
        map = new Car[height][width][];
    }

    @Override
    public void processQueue() {

    }

    @Override
    public boolean doYouAcceptThisCar(Car car) {
        return car.getSize() == 1;
    }
}