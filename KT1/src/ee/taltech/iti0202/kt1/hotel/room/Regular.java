package ee.taltech.iti0202.kt1.hotel.room;

public class Regular extends Room {
    private int numberOfComplaints;

    public Regular(int size, int number, boolean booked, int numberOfComplaints) {
        super(size, number, booked, Type.REGULAR);
        this.numberOfComplaints = numberOfComplaints;
    }

    @Override
    public boolean book() {
        if (!booked) {
            booked = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean unBook() {
        if (booked) {
            booked = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Regular room number %d of size %d with %d complaints made about it.",
                number, size, numberOfComplaints);
    }
}
