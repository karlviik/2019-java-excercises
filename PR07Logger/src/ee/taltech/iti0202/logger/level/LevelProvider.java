package ee.taltech.iti0202.logger.level;

/*
LevelFilter klassi jaoks vajalik interface, mille käest saab küsida levelit. Omab vaid ühte meetodit getLevel().
 */

public interface LevelProvider {
    Level getLevel();
}
