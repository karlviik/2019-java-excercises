package ee.taltech.iti0202.kt1.hotel.room;

public class Suite extends Room {
    private int bathCount;
    private int greatViewCount;

    public Suite(int size, int number, int bathCount, int greatViewCount) {
        super(size, number, Type.SUITE);
        this.bathCount = bathCount;
        this.greatViewCount = greatViewCount;
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
        return false;
    }

    @Override
    public String toString() {
        return String.format("Suite room number %d of size %d with %d baths and %d great views.",
                number, size, bathCount, greatViewCount);
    }
}
