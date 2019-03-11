package ee.taltech.iti0202.logger.formatter;
import ee.taltech.iti0202.logger.log.Log;
/*
LogFormatter on liides, millel on üks meetod format, meetod võtab sisendiks logi ning koostab sellest sõne.
Ühe abstraktse meetodiga liideseid nimetatakse funktsionaalseks liideseks.

Mall (tegelikult terve liidese kood):
 */
public interface LogFormatter {
    String format(Log log);
}
