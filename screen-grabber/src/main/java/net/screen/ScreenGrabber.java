package net.screen;

import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ScreenGrabber implements ImageGrabber {

    private final Robot robot;

    public ScreenGrabber(final Robot robot) {
        this.robot = robot;
    }

    @Override
    public BufferedImage grab(final Rectangle dimensions) {
        return robot.createScreenCapture(dimensions);
    }
}
