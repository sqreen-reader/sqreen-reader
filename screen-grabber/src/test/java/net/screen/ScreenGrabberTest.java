package net.screen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScreenGrabberTest {

    @Mock
    private Robot robot;

    @Mock
    private BufferedImage expectedImage;

    @DisplayName("Should grab image from screen")
    @Test
    void testGrab() {

        ImageGrabber imageGrabber = new ScreenGrabber(robot);

        Rectangle dimensions = new Rectangle();
        when(robot.createScreenCapture(dimensions)).thenReturn(expectedImage);

        BufferedImage capturedImage = imageGrabber.grab(dimensions);
        assertEquals(expectedImage, capturedImage);
    }

}