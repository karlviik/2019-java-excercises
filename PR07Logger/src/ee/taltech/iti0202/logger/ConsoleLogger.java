package ee.taltech.iti0202.logger;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

public class ConsoleLogger extends Logger {
    /*
    Kui eeltöö on tehtud, siis saab lõpuks teha klassi, mis päriselt midagi teeb (kuigi ka see klass teeb väga vähe).
    ConcoleLogger on Logger klassi alamklass, mis prindib log meetodile saadetud sõnumid konsooli.
     */
    public ConsoleLogger(String tag) {
        super(tag);
    }

    public ConsoleLogger(String tag, Level level) {
        super(...);
    }

    public ConsoleLogger(String tag, Level level, LogFormatter formatter) {
        // call also super here
    }

    public ConsoleLogger(String tag, LogFilter filter, LogFormatter formatter) {
        // same thing.
    }

    @Override
    protected void writeLog(String message) {
        // print this message to the console.
    }

}
