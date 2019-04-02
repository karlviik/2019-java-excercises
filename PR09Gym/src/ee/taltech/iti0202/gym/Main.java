package ee.taltech.iti0202.gym;

import ee.taltech.iti0202.gym.gym.Gym;
import ee.taltech.iti0202.gym.gym.classes.Class;
import ee.taltech.iti0202.gym.gym.client.Client;
import ee.taltech.iti0202.gym.gym.client.ClientBuilder;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Client client1 = new ClientBuilder("Peeter", "Jõgi").setAge(500).createClient();
        System.out.println(client1);
        System.out.println(client1.getCreditCardNumber());

        Gym zeGym = new Gym("This might be a gym");
        zeGym.addClient(client1);
        System.out.println(zeGym.getClients());

        zeGym.addClient(new ClientBuilder("Mittepeeter", "Mittejõgi").setAge(50).createClient());
        ArrayList<Client> clients = zeGym.getClients();

        zeGym.addClass(Class.newAgeClass(zeGym, "NoPeopleUnder51", "man who has seen the fall of Rome", 51));
        zeGym.addClass(Class.newNamelessClass(zeGym, "NoPeeterClass", "PeeterHater123", "peeter"));
        zeGym.addClass(Class.newLongNameClass(zeGym, "AtLeast7CharsInName", "1234567", 7));
        ArrayList<Class> classes = zeGym.getClasses();

        for (Class aClass : classes) {
            for (Client client : clients) {
                aClass.addParticipant(client);
            }
            System.out.println(aClass.toString());
        }
    }
}
