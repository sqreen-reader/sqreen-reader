package net.screen;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface ImageGrabber {
    BufferedImage grab(Rectangle dimensions);
}
