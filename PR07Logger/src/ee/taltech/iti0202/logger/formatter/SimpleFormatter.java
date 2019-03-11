package ee.taltech.iti0202.logger.formatter;
import ee.taltech.iti0202.logger.log.Log;
/*
SimpleFormatter implementeerib LogFormatter liidest.

Meetod format tagastab järgnevas formaadis sõne: "{log.level.name}\t{log.tag}\t{log.message}", kus \t tähistab tabulaatorit (tab).
 */
public class SimpleFormatter implements LogFormatter {

    @Override
    public String format(Log log) {
        return "INFO\t\I'm a tag!\tImplement me!"
    }

}
