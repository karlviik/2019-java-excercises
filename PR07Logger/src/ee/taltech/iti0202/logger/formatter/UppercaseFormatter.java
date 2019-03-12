package ee.taltech.iti0202.logger.formatter;

import ee.taltech.iti0202.logger.log.Log;

public class UppercaseFormatter extends SimpleFormatter {

    @Override
    public String format(Log log) {
        return super.format(log).toUpperCase();
    }
}
