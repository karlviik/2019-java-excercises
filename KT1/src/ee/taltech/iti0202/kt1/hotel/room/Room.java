package ee.taltech.iti0202.kt1.hotel.room;

public abstract class Room {
    public enum Type {
        SUITE,
        REGULAR
    }
    int size;
    int number;
    boolean booked = false;
    Type type;


    public Room(int size, int number, boolean booked, Type type) {
        this.size = size;
        this.number = number;
        this.booked = booked;
        this.type = type;
    }

    public abstract boolean book();

    public abstract boolean unBook();

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public Type getType() {
        return type;
    }
}
