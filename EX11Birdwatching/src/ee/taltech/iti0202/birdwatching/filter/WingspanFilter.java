package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class WingspanFilter implements BirdFilter {
    private double minWingspan;
    private double maxWingspan;

    public WingspanFilter(double minWingspan, double maxWingspan) {
        this.minWingspan = minWingspan;
        this.maxWingspan = maxWingspan;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream()
                .filter(x -> minWingspan <= x.getWingspan() && x.getWingspan() <= maxWingspan)
                .collect(Collectors.toList());
    }

    public double getMinWingspan() {
        return minWingspan;
    }

    public double getMaxWingspan() {
        return maxWingspan;
    }
}
