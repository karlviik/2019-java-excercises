package ee.taltech.iti0202.kt1.hotel;

import ee.taltech.iti0202.kt1.hotel.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * NOTE: this is unfinished, missing place where to show off, rest seems done-ish
 */
public class Hotel {
    private ArrayList<Room> rooms = new ArrayList<>();
    private String name;

    public Hotel(String name) {
        this.name = name;
    }

    public boolean addRoom(Room room) {
        if (!rooms.contains(room)) {
            rooms.add(room);
            return true;
        }
        return false;
    }

    public boolean unBook(Room room) {
        return room.unBook();
    }

    public Room bookRoom(Room.Type type) {
        return bookRoomOfSize(type, Integer.MAX_VALUE);
    }

    public Room bookRoomOfSize(Room.Type type, Integer size) {
        Optional<Room> room = rooms.stream().filter(x -> x.getSize() >= size && !x.isBooked() && type.equals(x.getType())).findFirst();
        if (room.isPresent()) {
            Room chosenRoom = room.get();
            chosenRoom.book();
            return chosenRoom;
        }
        return null;
    }

    public List getRoomsOfMinSize(int size) {
        return rooms.stream().filter(x -> x.getSize() >= size).collect(Collectors.toList());
    }

    public List getRooms() {
        return rooms;
    }

    public List getFreeRooms() {
        return rooms.stream().filter(x -> !x.isBooked()).collect(Collectors.toList());
    }

    public List getBookedRooms() {
        return rooms.stream().filter(Room::isBooked).collect(Collectors.toList());
    }
}
