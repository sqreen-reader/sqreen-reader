package net.sqreenreader.controller;

import javafx.application.Platform;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.qr.Barcode;
import net.sqreenreader.service.BarcodeParser;
import net.sqreenreader.url.HyperLinkOpener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.IOError;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SqreenReaderControllerTest {

    @Mock
    private BarcodeParser barcodeParser;

    @Mock
    private Toolkit toolkit;

    @Mock
    private HyperLinkOpener hyperLinkOpener;

    private Barcode expectedBarCode;

    private SqreenReaderController sqreenReaderController;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("glass.platform", "Monocle");
        System.setProperty("monocle.platform", "Headless");
        System.setProperty("prism.order", "sw");
        Platform.startup(()->{});
    }

    @BeforeEach
    void setup() throws IOException {
        expectedBarCode = new Barcode("www.google.com", ImageIO.read(getClass().getResourceAsStream("/test.jpg")));
        sqreenReaderController = new SqreenReaderController(barcodeParser, toolkit, hyperLinkOpener);
    }

    @Test
    @DisplayName("Should get barcode")
    void testGetCurrentBarcode() throws IOException {
        when(toolkit.getScreenSize()).thenReturn(new Dimension());
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(expectedBarCode);
        Barcode barcode = sqreenReaderController.getCurrentBarCode();
        assertEquals(expectedBarCode, barcode);
    }

    @Test
    @DisplayName("Should add barcode to scene")
    void testUpdateSceneWithBarCode() throws IOException, InterruptedException {
        when(toolkit.getScreenSize()).thenReturn(new Dimension());
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(expectedBarCode);
        Label noQRCode = new Label();
        Hyperlink latestQRCode = new Hyperlink();
        ImageView qrCodeImage = new ImageView();
        sqreenReaderController.setNoQRCode(noQRCode);
        sqreenReaderController.setLatestQRCode(latestQRCode);
        sqreenReaderController.setQrCodeImage(qrCodeImage);

        sqreenReaderController.updateSceneWithBarCode();
        TimeUnit.SECONDS.sleep(1);

        assertTrue(latestQRCode.isVisible());
        assertEquals(expectedBarCode.getText(), latestQRCode.getText());
        assertFalse(noQRCode.isVisible());
        assertTrue(qrCodeImage.isVisible());
        assertNotNull(qrCodeImage.getImage());
    }

    @Test
    @DisplayName("Should not update barcode if null")
    void testNullBarcode() throws IOException, InterruptedException {
        when(toolkit.getScreenSize()).thenReturn(new Dimension());
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(null);
        Label noQRCode = new Label();
        Hyperlink latestQRCode = new Hyperlink();
        sqreenReaderController.setNoQRCode(noQRCode);
        sqreenReaderController.setLatestQRCode(latestQRCode);

        sqreenReaderController.updateSceneWithBarCode();
        TimeUnit.SECONDS.sleep(1);

        assertTrue(noQRCode.isVisible());
    }

    @Test
    @DisplayName("Should open QR links")
    void testOpenLinks() throws IOException, URISyntaxException {
        Hyperlink latestQRCode = new Hyperlink();
        latestQRCode.setText("www.google.com");
        sqreenReaderController.setLatestQRCode(latestQRCode);
        sqreenReaderController.initialize();
        latestQRCode.fire();
        verify(hyperLinkOpener, times(1)).open("www.google.com");
    }

    @Test
    @DisplayName("Should throw error if cannot open links")
    void testDoNotOpenLinks() throws IOException, URISyntaxException {
        Hyperlink latestQRCode = new Hyperlink();
        latestQRCode.setText("www.google.com");
        sqreenReaderController.setLatestQRCode(latestQRCode);
        doThrow(new IOException()).when(hyperLinkOpener).open(anyString());
        sqreenReaderController.initialize();
        assertThrows(IOError.class, latestQRCode::fire);
    }

}