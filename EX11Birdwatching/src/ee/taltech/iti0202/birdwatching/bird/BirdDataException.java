package ee.taltech.iti0202.birdwatching.bird;

public class BirdDataException extends Exception {
    private final static String ERROR_MESSAGE = "Error handling bird data";

    public BirdDataException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
