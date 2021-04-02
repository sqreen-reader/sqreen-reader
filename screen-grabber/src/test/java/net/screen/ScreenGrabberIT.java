package net.screen;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ScreenGrabberIT {

    private File screenCapture = new File("target/screen.png");

    @BeforeEach
    void setUp() {
        Assumptions.assumeTrue(!isHeadless());
        screenCapture.delete();
    }

    private boolean isHeadless() {
        return "true".equals(System.getProperty("java.awt.headless"));
    }

    @Test
    @DisplayName("Should capture screen and save to file")
    void testGrabScreen() throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        ImageGrabber imageGrabber = new ScreenGrabber(new Robot());

        ImageIO.write(imageGrabber.grab(screenRect), "png", screenCapture);
        assertTrue(screenCapture.getTotalSpace() > 0);
    }
}
