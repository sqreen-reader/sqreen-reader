package net.sqreenreader.controller;

import javafx.fxml.FXML;
import net.sqreenreader.service.BarcodeParser;
import org.springframework.stereotype.Controller;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;
import javafx.scene.control.Label;

@Controller
public class SqreenReaderController {

    private final BarcodeParser barcodeParser;
    private final Toolkit toolkit;

    @FXML
    private Label noQRCode;

    @FXML
    private Label latestQRCode;

    public SqreenReaderController(final BarcodeParser barcodeParser, final Toolkit toolkit) {
        this.barcodeParser = barcodeParser;
        this.toolkit = toolkit;
    }


    public String getCurrentBarCode() throws IOException {
        return barcodeParser.parse(new Rectangle(toolkit.getScreenSize()));
    }

    public void updateSceneWithBarCode() throws IOException {
        String barCode = getCurrentBarCode();
        noQRCode.setVisible(false);
        latestQRCode.setVisible(true);
        latestQRCode.setText(barCode);
    }


    public void setNoQRCode(final Label noQRCode) {
        this.noQRCode = noQRCode;
    }

    public void setLatestQRCode(final Label latestQRCode) {
        this.latestQRCode = latestQRCode;
    }

}
