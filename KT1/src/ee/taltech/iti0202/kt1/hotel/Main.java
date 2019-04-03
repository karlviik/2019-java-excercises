package ee.taltech.iti0202.kt1.hotel;

import ee.taltech.iti0202.kt1.hotel.room.Regular;
import ee.taltech.iti0202.kt1.hotel.room.Room;
import ee.taltech.iti0202.kt1.hotel.room.Suite;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel of hotels");
        Room room1 = new Suite(10, 20, 2, 100);
        Room room2 = new Regular(10, 20, 3000);
        System.out.println(hotel.addRoom(room1));
        System.out.println(hotel.addRoom(room1));
        System.out.println(hotel.addRoom(room2));
        Room bookedRoom = hotel.bookRoom(Room.Type.REGULAR);
        System.out.println(bookedRoom);
        Room bookedRoom2 = hotel.bookRoomOfSize(Room.Type.SUITE, 11);
        System.out.println(bookedRoom2);
        System.out.println(hotel.unBook(bookedRoom2));
        Room bookedRoom3 = hotel.bookRoom(Room.Type.REGULAR);
        System.out.println(bookedRoom3);
        System.out.println(hotel.unBook(bookedRoom3));
        bookedRoom3 = hotel.bookRoom(Room.Type.REGULAR);
        System.out.println(bookedRoom3);
        System.out.println(hotel.getBookedRooms());
        System.out.println(hotel.getFreeRooms());
        System.out.println(hotel.getRooms());
    }
}
