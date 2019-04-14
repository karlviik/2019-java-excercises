package ee.taltech.iti0202.birdwatching.bird;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BirdDataController {
    private List<Bird> birds = new ArrayList<>();

    public void readBirdDataFromCsvFile(String filename) throws BirdDataException {
        Path path = Paths.get(filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            BirdBuilder bb = new BirdBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                List<String> fields = Arrays.asList(line.split(","));
                birds.add(
                        bb.setSpecies(fields.get(0))
                        .setWeight(Double.valueOf(fields.get(1)))
                        .setWingspan(Double.valueOf(fields.get(2)))
                        .setAge(Bird.Age.valueOf(fields.get(3).toUpperCase()))
                        .setSex(Bird.Sex.valueOf(fields.get(4).toUpperCase()))
                        .createBird()
                );

            }
        } catch (IOException e) {
            throw new BirdDataException(e.getMessage());
        } catch (Exception e) {
            throw new BirdDataException(e.getMessage());
        }
    }

    public List<Bird> getBirds() {
        return birds;
    }
}
