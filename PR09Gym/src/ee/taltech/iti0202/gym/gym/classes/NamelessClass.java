package ee.taltech.iti0202.gym.gym.classes;

import ee.taltech.iti0202.gym.gym.Gym;
import ee.taltech.iti0202.gym.gym.client.Client;

public class NamelessClass extends Class {
    private String bannedName;

    public NamelessClass(Gym gym, String name, String instructor, String bannedName) {
        super(gym, name, instructor);
        this.bannedName = bannedName;
    }

    public boolean addParticipant(Client client) {
        if (gym.getClients().contains(client)
                && !client.getFirstName().toLowerCase().equals(bannedName.toLowerCase())) {
            participants.add(client);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Class " + name + ", instructor " + instructor + ", no people with name " + bannedName
                + "\n Our participants are " + participants.toString();
    }
}
