package ee.taltech.iti0202.parking.parkinglot;

import ee.taltech.iti0202.parking.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Parking lot is a rectangular area with fixed with and height.
 * Well, rather 2 dimensions on the ground,
 * but as you represent in on the screen, then height can be seen as
 * the vertical axis.
 * The rectangle is filled with parking slots.
 * 3 x 4 parking lot has 12 slots.
 * The size of a slot is 2 units.
 * This means, that car with size 2 fits there perfectly.
 * Car with size 1 takes half the slot, so it could be
 * theoretically shared between 2 small cars.
 * Car with size 4 takes two consecutive slots.
 *
 * Each concrete parking lot type (subclass)
 * has its own rules about which cars it accepts
 * in its queue and how the queue is processed.
 * See the class description for more information.
 */
public abstract class ParkingLot {

    final int width;
    final int height;
    private int creationId;
    ArrayList<Car> parkedCars = new ArrayList<>();
    PriorityQueue<Car> queue = new PriorityQueue<>();

    /**
     * Initialize the parking slot with the given width and height.
     *
     * @param height Length of vertical side.
     * @param width Length of horizontal side.
     */
    public ParkingLot(int height, int width) {
        this.width = width;
        this.height = height;
    }


    /**
     * Adds a car to priority queue.
     * Car can be in a queue only once.
     * @param car Car to be added
     */
    public boolean addToQueue(Car car) {
        if (this.doYouAcceptThisCar(car) && car.getLocation() == null) {
            queue.add(car);
            car.setLocation(this);
            processQueue();
            return true;
        }
        return false;
    }

    /**
     * Processes the queue.
     *
     * The cars are taken from the queue in specified order.
     * If the first car in the queue cannot be parked
     * the process will wait. Also, if the queue is empty, process waits.
     * Otherwise the process should be "running" all the time.
     * In reality you should call this method from other methods
     * which could initialize the process.
     *
     */
    public abstract void processQueue();

    /**
     * Returns a list of parked cars in the order they were received from the queue.
     * @return A list of parked cars.
     */
    public List<Car> getParkedCars() {
        return parkedCars;
    }

    /**
     * Returns string presentation of the parking lot.
     *
     * Each slot takes 2x2 chars.
     * Size 1 is represented by 1x2 (height, width) area
     * Empty slot is represented by dots (.):
     *
     * Empty table with width 3, height 2:
     * ......
     * ......
     * ......
     * ......
     *
     * One large priority car:
     * P4P4..
     * P4P4..
     * ......
     * ......
     *
     * + one small highest priority car:
     * P4P4H1
     * P4P4..
     * ......
     * ......
     *
     * + medium common car:
     * P4P4H1
     * P4P4..
     * C2....
     * C2....
     *
     * @return String representation of the parking lot
     */
    public abstract String getTable();

    public int getCreationId() {
        return creationId;
    }

    public PriorityQueue<Car> getQueue() {
        return queue;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public abstract boolean doYouAcceptThisCar(Car car);

    public abstract void unparkCar(Car car);

    public void setCreationId(int creationId) {
        this.creationId = creationId;
    }
}
