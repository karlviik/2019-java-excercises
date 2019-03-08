
package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

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
    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of verical side.
     * @param width  Length of horizontal side.
     * @param levels Number of levels.
     */
    public MultiLevelParkingLot(int height, int width, int levels) {
        super(height, width);
    }

    @Override
    public void processQueue() {

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
        return super.getTable();
    }

    @Override
    public boolean doYouAcceptThisCar(Car car) {
        return queue.size() < 10;
    }
}
