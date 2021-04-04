package net.qr;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface BarCodeReader {
    String read(BufferedImage image) throws IOException;
}
