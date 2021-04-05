package net.sqreenreader.service;

import net.qr.BarCodeReader;
import net.screen.ImageGrabber;

import java.awt.Rectangle;
import java.io.IOException;

public class BarcodeParserService implements ImageParser {

    private final ImageGrabber imageGrabber;

    private final BarCodeReader barCodeReader;

    public BarcodeParserService(final ImageGrabber imageGrabber, final BarCodeReader barCodeReader) {
        this.imageGrabber = imageGrabber;
        this.barCodeReader = barCodeReader;
    }

    @Override
    public String parse(final Rectangle dimensions) throws IOException {
       return barCodeReader.read(imageGrabber.grab(dimensions));
    }
}
