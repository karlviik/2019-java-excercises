package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class WeightFilter implements BirdFilter {
    private double minWeight;
    private double maxWeight;

    public WeightFilter(double minWeight, double maxWeight) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream()
                .filter(x -> minWeight <= x.getWeight() && x.getWeight() <= maxWeight)
                .collect(Collectors.toList());
    }

    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
