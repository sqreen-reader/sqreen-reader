package net.sqreenreader.controller;

import net.sqreenreader.service.BarcodeParser;
import org.springframework.stereotype.Controller;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;

@Controller
public class SqreenReaderController {

    private final BarcodeParser barcodeParser;
    private final Toolkit toolkit;

    public SqreenReaderController(final BarcodeParser barcodeParser, final Toolkit toolkit) {
        this.barcodeParser = barcodeParser;
        this.toolkit = toolkit;
    }

    public String getCurrentBarCode() throws IOException {
        return barcodeParser.parse(new Rectangle(toolkit.getScreenSize()));
    }

}
