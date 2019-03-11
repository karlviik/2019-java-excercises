package ee.taltech.iti0202.logger;

import ee.taltech.iti0202.logger.filter.LevelFilter;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.formatter.SimpleFormatter;
import ee.taltech.iti0202.logger.level.Level;
import ee.taltech.iti0202.logger.log.Log;

public abstract class Logger {
    /*
    Nüüd, kui logimiseks vajalikud abimeetodid on kirjutatud saame teha abstraktse klassi Logger.

    Loggeril on kindlasti vaja

    *tag, silt või märgistus aitab eristada erinevate loggerite logitud teksti. Näiteks võib kasutaja teha igale
    * klassile eraldi loggeri, kuid logida kõik logid ühte faili või konsooli.
    *LogFormatteri, et logisi õigel kujul kuvada.
    *LogFilter'it, et logisi filtreerida.
    Loggerit saab teha konstruktoriga, kus kõik kolm välja on kasutaja poolt määratud.

    public Logger(String tag, LogFilter filter, LogFormatter formatter) {}
    Tavaliselt filtreeritakse logisi leveli järgi. Seega võiksime teha konstruktori, mis võtab filtri asemel
    sisendiks leveli, ning teeb selle põhjal LevelFilter klassi.

    LevelFilter konstruktor nõuab sisendiks LevelProviderit. LevelProvider võimaldab filtri klassi dünaamiliselt
    muuta. Kuna me ei taha seda dünaamiliselt muuta siis võime teha lambda, mis tagatab kogu aeg ühte levelit.

    public Logger(String tag, Level level, LogFormatter formatter) {}
    Funktsionaalse liidese näide.
    Kui liidesel on vaid üks meetod, siis saab liidest implementeerida lambdana. Lambda käitub nagu ilma nimeta klass.
    Funktsionaalsete liidestega oled kokku puutunud sorteerides ja streame kasutades.

    interface Square
    {
        int calculate(int x);
    }
    class Test
    {
        public static void main(String args[])
        {
            int a = 5;
            Square s = (int x)->x*x; // lambda expression to define the calculate method
            int ans = s.calculate(a); // use s like regular object.
            System.out.println(ans);
        }
    }
    ... tagasi konstruktorite juurde.

    Mugavuse huvides lisame juurde veel 2 konstruktorit.

    Üks konstruktor võtab sisendiks 2 parameetrit: tag ja tase. LogFormatter objekti algväärtustab SimpleFormatter instantsiga.

    public Logger(String tag, Level level) { }
    Viimane konstruktor on kõige laisem. See konstruktor küsib vaid tagi. Samas on see ka laisalt programmeeritud.
    See konstruktor annab objekti loomiseks käsu järgmisele konstruktorile.

    public Logger(String tag) {
        this(tag, Level.WARNING);
    }
    Kavalalt programmi koostades näevad kolm konstruktorit sellised üherealise sisuga välja,
    ning lõpuks loob objekti esimesena kirjeldatud kõige detailsem konstruktor.

    Lisaks konstruktorile on loggeril olemas ka logimise meetod. Meetod küsib filtri käest, kas sõnum tuleks logida.
    Kui sõnum tuleb logida, siis küsib meetod formaatija käest vormistatud sõnumit ning annab alamklassile lõpliku sõnumi, mida logida.

    public final void log(Level level, String message) {
        // check if should log
        // get formatted message from formatter
        // tell subclass to log the message.
    }
    Loggeri alamklassidele on jäetud implementeerida meetod log(String message). Kuna need sõnumid on kontrollitud,
    peab alamklass neid kindlasti logima.

    protected abstract void writeLog(String message);
    Lisaks on Loggeris mugavusmeetodid, mis kannavad erinevate levelite nimesi ja teevad neile vastava leveliga logi.
     */
    /**
     * Creates logger that logs messages with Level.Warning or higher.
     *
     * @param tag unique identifier of given logger.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag) {
        this(tag, Level.WARNING);
    }

    /**
     * Creates logger that logs from given level.
     *
     * @param tag   unique identifier of given logger.
     * @param level minimum logging level.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag, Level level) {
        this(...);
    }

    /**
     * Creates logger that logs from given level.
     *
     * @param tag       unique identifier of given logger.
     * @param level     minimum logging level.
     * @param formatter custom formatter.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag, Level level, LogFormatter formatter) {
        this(...);
    }

    /**
     * Create logger with custom filter.
     *
     * @param tag       unique identifier of given logger.
     * @param filter    custom filter
     * @param formatter custom formatter.
     */
    public Logger(String tag, LogFilter filter, LogFormatter formatter) { }

    /**
     * Logs the message.
     */
    public final void log(Level level, String message) { }

    /**
     * Abstract method that is called with formatted message.
     * This message has passed user provided filter and should be logged.
     */
    protected abstract void writeLog(String message);

    /**
     * Creates log with Level.SEVERE
     */
    public final void severe(String message) { }

    /**
     * Creates log with Level.ERROR
     */
    public final void error(String message) { }

    /**
     * Creates log with Level.WARNING
     */
    public final void warning(String message) { }

    /**
     * Creates log with Level.INFO
     */
    public final void info(String message) { }

    /**
     * Creates log with Level.DEBUG
     */
    public final void debug(String message) { }

}