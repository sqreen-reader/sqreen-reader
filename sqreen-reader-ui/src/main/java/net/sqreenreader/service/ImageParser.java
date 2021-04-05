package net.sqreenreader.service;

import java.awt.Rectangle;
import java.io.IOException;

public interface ImageParser {
    String parse(Rectangle dimensions) throws IOException;
}
