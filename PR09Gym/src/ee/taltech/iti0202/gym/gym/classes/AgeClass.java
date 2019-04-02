package ee.taltech.iti0202.gym.gym.classes;

import ee.taltech.iti0202.gym.gym.Gym;
import ee.taltech.iti0202.gym.gym.client.Client;

public class AgeClass extends Class {
    private Integer age;

    public AgeClass(Gym gym, String name, String instructor, Integer age) {
        super(gym, name, instructor);
        this.age = age;
    }

    public boolean addParticipant(Client client) {
        if (gym.getClients().contains(client) && client.getAge() != null && client.getAge() < age) {
            participants.add(client);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Class " + name + ", instructor " + instructor + ", no people under " + age
                + "\n Our participants are " + participants.toString();
    }
}
