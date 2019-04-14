package ee.taltech.iti0202.birdwatching.bird;

public class Bird {
    public enum Age {
        ADULT,
        YOUNGLING
    }
    public enum Sex {
        MALE,
        FEMALE,
        UNKNOWN
    }
    private String species;
    private Double weight;
    private Double wingspan;
    private Age age;
    private Sex sex;

    public Bird(String species, Double weight, Double wingspan, Age age, Sex sex) {
        this.species = species;
        this.weight = weight;
        this.wingspan = wingspan;
        this.age = age;
        this.sex = sex;
    }

    public String getSpecies() {
        return species;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public Age getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }
}
