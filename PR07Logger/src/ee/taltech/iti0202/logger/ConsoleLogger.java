package ee.taltech.iti0202.logger;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

public class ConsoleLogger extends Logger {

    public ConsoleLogger(String tag) {
        super(tag);
    }

    public ConsoleLogger(String tag, Level level) {
        super(tag, level);
    }

    public ConsoleLogger(String tag, Level level, LogFormatter formatter) {
        super(tag, level, formatter);
    }

    public ConsoleLogger(String tag, LogFilter filter, LogFormatter formatter) {
        super(tag, filter, formatter);
    }

    @Override
    protected void writeLog(String message) {
        System.out.println(message);
    }

}
