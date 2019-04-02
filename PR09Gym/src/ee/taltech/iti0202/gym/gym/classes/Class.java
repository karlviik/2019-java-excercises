package ee.taltech.iti0202.gym.gym.classes;

import ee.taltech.iti0202.gym.gym.Gym;
import ee.taltech.iti0202.gym.gym.client.Client;

import java.util.ArrayList;

public abstract class Class {
    final Gym gym;
    String name;
    String instructor;
    ArrayList<Client> participants = new ArrayList<>();

    protected Class(Gym gym, String name, String instructor) {
        this.gym = gym;
        this.name = name;
        this.instructor = instructor;
    }

    public abstract boolean addParticipant(Client client);

    public boolean removeParticipant(Client client) {
        if (participants.contains(client)) {
            participants.remove(client);
            return true;
        }
        return false;
    }

    public Gym getGym() {
        return gym;
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }

    public static Class newAgeClass(Gym gym, String name, String instructor, Integer minAge) {
        return new AgeClass(gym, name, instructor, minAge);
    }

    public static Class newLongNameClass(Gym gym, String name, String instructor, Integer minLength) {
        return new LongNameClass(gym, name, instructor, minLength);
    }

    public static Class newNamelessClass(Gym gym, String name, String instructor, String bannedName) {
        return new NamelessClass(gym, name, instructor, bannedName);
    }
}
