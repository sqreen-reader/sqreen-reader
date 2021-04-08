package net.qr;

import java.awt.image.BufferedImage;

public class Barcode {
    private final String text;
    private final BufferedImage image;

    public Barcode(final String text, final BufferedImage image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public BufferedImage getImage() {
        return image;
    }
}
