package ee.taltech.iti0202.tk1.cat;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<Cat> cats;

    public Person() {
        cats = new ArrayList<>();
    }

    public List<Cat> getCats() {
        return cats;
    }

    public boolean addCat(Cat cat) {
        if (!cats.contains(cat)) {
            cats.add(cat);
            return true;
        }
        return false;
    }

    boolean sellCat(Person sellTo, Cat cat) {
        if (sellTo == this || !cats.contains(cat)) {
            return false;
        }
        sellTo.addCat(cat);
        cats.remove(cat);
        return true;
    }
}
