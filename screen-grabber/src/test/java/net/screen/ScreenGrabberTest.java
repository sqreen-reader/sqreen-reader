package net.screen;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScreenGrabberTest {

    @DisplayName("Should be Constructed")
    @Test
    public void newScreenGrabberTest() {
        assertTrue(new ScreenGrabber() instanceof ImageGrabber);
    }

}