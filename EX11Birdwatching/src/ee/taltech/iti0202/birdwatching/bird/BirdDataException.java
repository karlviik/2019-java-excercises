package ee.taltech.iti0202.birdwatching.bird;

public class BirdDataException extends Exception {
    String customMessage;

    BirdDataException(String customMessage) {
        this.customMessage = customMessage;
    }

    @Override
    public String toString() {
        return "Error handling bird data: " + customMessage;
    }
}
