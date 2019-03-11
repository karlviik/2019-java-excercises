package ee.taltech.iti0202.logger.level;
/*
See klass aitab loggeril mõista, kui tähtis on saadetud sõnum. Leveli järgi saab logisi filtreerida. Näiteks võib jätta ebatähtsad logid kasutajale näitamata.

Klassi teeb eriliseks see, et sellel on private konstruktor. Konstruktoriga antakse Levelile nimetus ja täisarvuline väärtus.

Samuti on klass märgitud finaliks, see tähendab, et antud klassist ei saa teha alamklasse.

Kuna klass on final ning omab vaid private konstruktorit, on ainuke võimalus luua Level objekte klassi sees.
 */
public final class Level {

    public static final Level OFF = new Level("OFF", Integer.MAX_VALUE);
    public static final Level SEVERE = new Level("SEVERE", 800);
    public static final Level ERROR = new Level("ERROR", 700);
    public static final Level WARNING = new Level("WARNING", 500);
    public static final Level INFO = new Level("INFO", 400);
    public static final Level DEBUG = new Level("DEBUG", 300);
    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE);

    private Level(String name, int value) {
        // TODO
    }

    public String getName() {
        return null;
    }

    public int getValue() {
        return -1;
    }

}