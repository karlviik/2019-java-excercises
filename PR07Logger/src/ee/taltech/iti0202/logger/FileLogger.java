package ee.taltech.iti0202.logger;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLogger extends Logger {

    private Path logFilePath;

    public FileLogger(String tag, String logFilePath) {
        super(tag);
        this.logFilePath = Paths.get(logFilePath);
    }

    public FileLogger(String tag, String logFilePath, Level level) {
        super(tag, level);
        this.logFilePath = Paths.get(logFilePath);
    }

    public FileLogger(String tag, String logFilePath, Level level, LogFormatter formatter) {
        super(tag, level, formatter);
        this.logFilePath = Paths.get(logFilePath);
    }

    public FileLogger(String tag, String logFilePath, LogFilter filter, LogFormatter formatter) {
        super(tag, filter, formatter);
        this.logFilePath = Paths.get(logFilePath);
    }

    @Override
    protected void writeLog(String message) {
        try (
             BufferedWriter writer = Files.newBufferedWriter(logFilePath,
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
