package ee.taltech.iti0202.gym.gym.classes;

import ee.taltech.iti0202.gym.gym.Gym;
import ee.taltech.iti0202.gym.gym.client.Client;

public class LongNameClass extends Class {
    private Integer minLength;

    public LongNameClass(Gym gym, String name, String instructor, Integer minLength) {
        super(gym, name, instructor);
        this.minLength = minLength;
    }

    public boolean addParticipant(Client client) {
        if (gym.getClients().contains(client) && client.getFirstName().length() >= minLength) {
            participants.add(client);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Class " + name + ", instructor " + instructor + ", no people with name length of less than  "
                + minLength + "\n Our participants are " + participants.toString();
    }
}
