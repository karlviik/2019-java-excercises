package ee.taltech.iti0202.birdwatching.bird;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BirdDataController {
    private List<Bird> birds = new ArrayList<>();

    public void readBirdDataFromCsvFile(String filename) throws BirdDataException {
        Path path = Paths.get(filename);
        BirdBuilder bb = new BirdBuilder();
        try (Stream<String> lines = Files.lines(path)) {
            lines.map(x -> x.split(","))
            .forEach(fields -> birds.add(
                new BirdBuilder()
                    .setSpecies(fields[0])
                    .setWeight(Double.valueOf(fields[1]))
                    .setWingspan(Double.valueOf(fields[2]))
                    .setAge(Bird.Age.valueOf(fields[4].toUpperCase()))
                    .setSex(Bird.Sex.valueOf(fields[3].toUpperCase()))
                    .createBird()
            ));
        } catch (IOException e) {
            throw new BirdDataException(e);
        }
    }

    public List<Bird> getBirds() {
        return birds;
    }
}
