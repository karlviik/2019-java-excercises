package ee.taltech.iti0202.birdwatching.bird;

import java.awt.event.FocusEvent;

public class BirdDataException extends Exception {



    public BirdDataException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "Error handling bird data.";
    }
}
