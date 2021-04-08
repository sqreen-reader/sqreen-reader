package net.sqreenreader.service;

import net.qr.Barcode;

import java.awt.Rectangle;
import java.io.IOException;

public interface BarcodeParser {
    Barcode parse(Rectangle dimensions) throws IOException;
}
