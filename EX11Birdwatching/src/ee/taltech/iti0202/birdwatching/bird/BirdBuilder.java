package ee.taltech.iti0202.birdwatching.bird;

public class BirdBuilder {
    private String species;
    private Double weight;
    private Double wingspan;
    private Bird.Age age;
    private Bird.Sex sex;

    public BirdBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }

    public BirdBuilder setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public BirdBuilder setWingspan(Double wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    public BirdBuilder setAge(Bird.Age age) {
        this.age = age;
        return this;
    }

    public BirdBuilder setSex(Bird.Sex sex) {
        this.sex = sex;
        return this;
    }

    public Bird createBird() {
        return new Bird(species, weight, wingspan, age, sex);
    }
}
