package net.sqreenreader.service;

import java.awt.Rectangle;
import java.io.IOException;

public interface BarcodeParser {
    String parse(Rectangle dimensions) throws IOException;
}
