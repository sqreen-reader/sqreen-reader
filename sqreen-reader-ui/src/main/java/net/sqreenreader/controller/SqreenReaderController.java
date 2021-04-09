package net.sqreenreader.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import net.qr.Barcode;
import net.sqreenreader.service.BarcodeParser;
import net.sqreenreader.url.HyperLinkOpener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.control.Label;

@Controller
public class SqreenReaderController {

    public static final int REFRESH_RATE = 1000;

    private final BarcodeParser barcodeParser;
    private final Toolkit toolkit;
    private final HyperLinkOpener hyperLinkOpener;

    @FXML
    private Label noQRCode;

    @FXML
    private Hyperlink latestQRCode;

    @FXML
    private ImageView qrCodeImage;

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

    @Scheduled(fixedRate = REFRESH_RATE)
    public void updateSceneWithBarCode() throws IOException {
        Barcode barCode = getCurrentBarCode();
        if (barCode != null) {
            updateLabels(barCode);
        }
    }

    private void updateLabels(final Barcode barCode) {
        Platform.runLater(() -> {
            noQRCode.setVisible(false);
            setQrText(barCode.getText());
            setQrImage(barCode.getImage());
        });
    }

    private void setQrText(final String text) {
        latestQRCode.setVisible(true);
        latestQRCode.setText(text);
    }

    private void setQrImage(final BufferedImage image) {
        qrCodeImage.setVisible(true);
        qrCodeImage.setImage(SwingFXUtils.toFXImage(image, null));
    }

    public void setNoQRCode(final Label noQRCode) {
        this.noQRCode = noQRCode;
    }

    public void setLatestQRCode(final Hyperlink latestQRCode) {
        this.latestQRCode = latestQRCode;
    }

    public void setQrCodeImage(final ImageView qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }
}
