package net.sqreenreader.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import net.qr.Barcode;
import net.sqreenreader.service.BarcodeParser;
import net.sqreenreader.url.HyperLinkOpener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOError;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.Label;

@Controller
public class SqreenReaderController {

    private final BarcodeParser barcodeParser;
    private final Toolkit toolkit;
    private final HyperLinkOpener hyperLinkOpener;

    @FXML
    private Label noQRCode;

    @FXML
    private Hyperlink latestQRCode;

    public SqreenReaderController(final BarcodeParser barcodeParser, final Toolkit toolkit,
                                  final HyperLinkOpener hyperLinkOpener) {
        this.barcodeParser = barcodeParser;
        this.toolkit = toolkit;
        this.hyperLinkOpener = hyperLinkOpener;
    }

    @FXML
    protected void initialize() {
        latestQRCode.setOnAction(actionEvent -> openLink());
    }

    private void openLink() {
        try {
            hyperLinkOpener.open(latestQRCode.getText());
        } catch (IOException | URISyntaxException e) {
            throw new IOError(e);
        }
    }


    public Barcode getCurrentBarCode() throws IOException {
        return barcodeParser.parse(new Rectangle(toolkit.getScreenSize()));
    }

    @Scheduled(fixedRate = 1)
    public void updateSceneWithBarCode() throws IOException {
        Barcode barCode = getCurrentBarCode();
        if (barCode != null) {
            updateLabels(barCode.getText());
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
