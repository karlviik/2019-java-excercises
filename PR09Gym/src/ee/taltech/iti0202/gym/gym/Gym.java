package ee.taltech.iti0202.gym.gym;

import ee.taltech.iti0202.gym.gym.client.Client;
import ee.taltech.iti0202.gym.gym.classes.Class;

import java.util.ArrayList;

public class Gym {
    private final String name;
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Class> aClasses = new ArrayList<>();

    public Gym(String name) {
        this.name = name;
    }

    public boolean addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
            return true;
        }
        return false;
    }

    public boolean removeClient(Client client) {
        if (clients.contains(client)) {
            for (Class aClass : aClasses) {
                aClass.removeParticipant(client);
            }
            clients.remove(client);
            return true;
        }
        return false;
    }

    public void addClass(Class aClass) {
        aClasses.add(aClass);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Class> getClasses() {
        return aClasses;
    }

    public String getName() {
        return name;
    }
}
