package ee.taltech.iti0202.parking.car;

import ee.taltech.iti0202.parking.parkinglot.ParkingLot;

/**
 * Represents a car with priority and size.
 * The size can be one of 1, 2, 4 (the code doesn't have to validate it).
 * This class implements Comparable interface.
 * This allows objects to be sorted in priority queue (or for sorting in general).
 * Cars with highest priority will be taken first, then with the "priority" priority
 * and then all the common cars.
 * If there are cars with the same priority, prefer cars with lower size.
 * So highest-1 (priority status-size) comes before highest-2 which comes before priority-1.
 */
public class Car implements Comparable<Car> {
    private PriorityStatus status;
    private int size;
    private ParkingLot location;
    private boolean isParked = false;
    public static final String[] POSSIBLE_STRINGS = {"H1", "H2", "H4", "P1", "P2", "P4", "C1", "C2", "C4"};


    public enum PriorityStatus {
        HIGHEST, PRIORITY, COMMON
    }

    @Override
    public int compareTo(Car o) {
        if (this.status == PriorityStatus.HIGHEST && o.status != PriorityStatus.HIGHEST) {
            return -1;
        } else if (this.status == PriorityStatus.COMMON && o.status != PriorityStatus.COMMON) {
            return 1;
        } else if (this.status == PriorityStatus.PRIORITY) {
            if (o.status == PriorityStatus.HIGHEST) {
                return 1;
            } else if (o.status == PriorityStatus.COMMON) {
                return -1;
            }
        }
        return Integer.compare(this.size, o.size);
    }

    public Car(PriorityStatus status, int size) {
        this.status = status;
        this.size = size;
        location = null;
    }

    /**
     * Gets the priority of the car.
     * @return PriorityStatus
     */
    public PriorityStatus getPriorityStatus() {
        return status;
    }

    /**
     * Gets the size of the car.
     * @return Size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Finish parking. This car has finished parking.
     * The car should be removed from parking lot
     * (its slots will be empty).
     * Returns false, if the car is not parked currently.
     * Otherwise returns true.
     * @return True if the car was parking, false otherwise.
     */
    public boolean unpark() {
        if (location == null) {
            return false;
        }
        location.unparkCar(this);
        location = null;
        isParked = false;
        return true;
    }

    public void setParked() {
        isParked = true;
    }

    public ParkingLot getLocation() {
        return location;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setLocation(ParkingLot parkingLot) {
        location = parkingLot;
    }

    @Override
    public String toString() {
        String output = "";
        switch (status) {
            case HIGHEST:
                output += "H";
                break;
            case PRIORITY:
                output += "P";
                break;
            case COMMON:
                output += "C";
                break;
            default:
                output += "thestylecheckwantsmetodothisanditistotallynotrequiredbecausetherearenootheroptions";
                break;
        }
        output += size;
        return output;
    }
}
