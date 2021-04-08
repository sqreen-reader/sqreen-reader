package net.sqreenreader.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import net.sqreenreader.service.BarcodeParser;
import org.springframework.scheduling.annotation.Scheduled;
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
    private Hyperlink latestQRCode;

    public SqreenReaderController(final BarcodeParser barcodeParser, final Toolkit toolkit) {
        this.barcodeParser = barcodeParser;
        this.toolkit = toolkit;
    }


    public String getCurrentBarCode() throws IOException {
        return barcodeParser.parse(new Rectangle(toolkit.getScreenSize()));
    }

    @Scheduled(fixedRate = 1)
    public void updateSceneWithBarCode() throws IOException {
        String barCode = getCurrentBarCode();
        if (barCode != null) {
            updateLabels(barCode);
        }
    }

    private void updateLabels(final String barCode) {
        Platform.runLater(() -> {
            noQRCode.setVisible(false);
            latestQRCode.setVisible(true);
            latestQRCode.setText(barCode);
        });
    }


    public void setNoQRCode(final Label noQRCode) {
        this.noQRCode = noQRCode;
    }

    public void setLatestQRCode(final Hyperlink latestQRCode) {
        this.latestQRCode = latestQRCode;
    }

}
