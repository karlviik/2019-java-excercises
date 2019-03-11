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

    public FileLogger(String tag, String logFilePath) {
        // super + do something with logFilePath
    }

    public FileLogger(String tag, String logFilePath, Level level) {
        // super + do something with logFilePath
    }

    public FileLogger(String tag, String logFilePath, Level level, LogFormatter formatter) {
        // super + do something with logFilePath
    }

    public FileLogger(String tag, String logFilePath, LogFilter filter, LogFormatter formatter) {
        // super + do something with logFilePath
    }

    @Override
    protected void writeLog(String message) {
        // append to log file if exists
        // if doesn't exist create file
    }
}