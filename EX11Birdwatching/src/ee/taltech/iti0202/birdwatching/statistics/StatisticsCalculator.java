package ee.taltech.iti0202.birdwatching.statistics;
import ee.taltech.iti0202.birdwatching.bird.Bird;
import ee.taltech.iti0202.birdwatching.bird.BirdDataController;
import ee.taltech.iti0202.birdwatching.bird.BirdDataException;
import ee.taltech.iti0202.birdwatching.filter.BirdFilter;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsCalculator {
    private BirdDataController controller;
    private BirdFilter filter;
    private List<Bird> birds = new ArrayList<>();

    public StatisticsCalculator(BirdDataController controller, BirdFilter filter) {
        this.controller = controller;
        this.filter = filter;
    }

    public void prepareBirdData(String filename) {
        try {
            controller.readBirdDataFromCsvFile(filename);
            birds = filter.getSuitableBirds(controller.getBirds());

        } catch (BirdDataException e) {
            System.out.println("PROBLEM HANDLING BIRD DATA");
        }
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public long countBirds() {
        // could use .stream().count(); , but that's excessive
        return getBirds().size();
    }

    public OptionalDouble findAverageWeight() {
        return getBirds().stream()
                .mapToDouble(Bird::getWeight)
                .average();
    }

    public OptionalDouble findMinWeight() {
        return getBirds().stream()
                .mapToDouble(Bird::getWeight)
                .min();
    }

    public OptionalDouble findMaxWeight()  {
        return getBirds().stream()
                .mapToDouble(Bird::getWeight)
                .max();
    }

    public OptionalDouble findAverageWingspan() {
        return getBirds().stream()
                .mapToDouble(Bird::getWingspan)
                .average();
    }

    public OptionalDouble findMinWingspan() {
        return getBirds().stream()
                .mapToDouble(Bird::getWingspan)
                .min();
    }

    public OptionalDouble findMaxWingspan() {
        return getBirds().stream()
                .mapToDouble(Bird::getWingspan)
                .max();
    }

    public List<Double> getWingspanData() {
        return getBirds().stream()
                .map(Bird::getWingspan)
                .collect(Collectors.toList());
    }

    public List<Double> getWeightDataInGrams() {
        return getBirds().stream()
                .map(x -> x.getWeight() * 1000)
                .collect(Collectors.toList());
    }

    public List<Bird> getSample(int sampleSize) {
        return getBirds().stream()
                .limit(sampleSize)
                .collect(Collectors.toList());
    }

    public Optional<Bird> findSampleBird(String species, Bird.Sex sex, Bird.Age age) {
        return getBirds().stream()
                .filter(x -> x.getSpecies().toLowerCase().equals(species.toLowerCase()))
                .filter(x -> x.getSex().equals(sex))
                .filter(x -> x.getAge().equals(age))
                .findFirst();
    }

    public Set<String> getAllEncounteredSpecies() {
        return getBirds().stream()
                .map(Bird::getSpecies)
                .collect(Collectors.toSet());
    }

    public List<Bird> getLargeBirds(int skipCount) {
        return getBirds().stream()
                .sorted()
                .skip(skipCount)
                .collect(Collectors.toList());
    }

    public Map<String, List<Bird>> mapBirdsToSpecies() {
        return getAllEncounteredSpecies().stream()
                .map(x -> Map.entry(x, getBirds().stream()
                        .filter(y -> y.getSpecies().equals(x))
                        .collect(Collectors.toList())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
