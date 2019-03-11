package ee.taltech.iti0202.logger.filter;
import ee.taltech.iti0202.logger.level.LevelProvider;
import ee.taltech.iti0202.logger.log.Log;
/*
Klass, mis implementeerib LogFilter liidest. Klass saab konstruktoriga kaasa LevelProvider tüüpi objekti,
mille käest saab küsida levelit, mille väärtusest alates soovitakse logi väärtust kuvada.
 */
public class LevelFilter implements LogFilter {

    private LevelProvider levelProvider;

    public LevelFilter(LevelProvider levelProvider) {
        this.levelProvider = levelProvider;
    }

    @Override
    public boolean isLoggable(Log log) {
        return false; // should log if levelProviders.log.value is less or equal to Log.level.value
    }

}