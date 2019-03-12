package ee.taltech.iti0202.logger;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

public class FileLogger extends Logger {

    /*
    Logger, mis kirjutab logid faili. Kui antud nimega faili ei ole siis teeb uue faili, muul juhul lisab faili lõppu uue logi.

    Faili kirjutamise kohta saad lugeda javadocist.
    https://ained.ttu.ee/javadoc/output_file.html
     */
    private String logFilePath;

    public FileLogger(String tag, String logFilePath) {
        super(tag);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, Level level) {
        super(tag, level);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, Level level, LogFormatter formatter) {
        super(tag, level, formatter);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, LogFilter filter, LogFormatter formatter) {
        super(tag, filter, formatter);
        this.logFilePath = logFilePath;
    }

    @Override
    protected void writeLog(String message) {
        // append to log file if exists
        // if doesn't exist create file
    }
}