package ee.taltech.iti0202.files.input;
import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        LinkedList<String> lines = new LinkedList<>();
        Path path = Paths.get(filename);
        // Path path = Paths.get("EX06Files", "src", filename);  // TODO :: remove this
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                line = line.replace("\n", "");
                lines.add(line);
            }
        } catch (Exception e) {
            throw new FileReaderException("No such file", e);
        }
        return lines;
    }
}