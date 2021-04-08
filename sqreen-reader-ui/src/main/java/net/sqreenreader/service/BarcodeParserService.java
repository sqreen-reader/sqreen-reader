package net.sqreenreader.service;

import net.qr.BarCodeReader;
import net.screen.ImageGrabber;
import org.springframework.stereotype.Service;

import java.awt.Rectangle;
import java.io.IOException;

@Service
public class BarcodeParserService implements BarcodeParser {

    private final ImageGrabber imageGrabber;

    private final BarCodeReader barCodeReader;

    public BarcodeParserService(final ImageGrabber imageGrabber, final BarCodeReader barCodeReader) {
        this.imageGrabber = imageGrabber;
        this.barCodeReader = barCodeReader;
    }

    @Override
    public String parse(final Rectangle dimensions) throws IOException {
       return barCodeReader.read(imageGrabber.grab(dimensions)).getText();
    }
}
