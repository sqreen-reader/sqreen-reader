package net.sqreenreader.controller;

import javafx.application.Platform;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import net.sqreenreader.service.BarcodeParser;
import net.sqreenreader.url.HyperLinkOpener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SqreenReaderControllerTest {

    @Mock
    private BarcodeParser barcodeParser;

    @Mock
    private Toolkit toolkit;

    @Mock
    private HyperLinkOpener hyperLinkOpener;

    private String expectedBarcodeData = "www.google.com";

    private SqreenReaderController sqreenReaderController;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("glass.platform", "Monocle");
        System.setProperty("monocle.platform", "Headless");
        System.setProperty("prism.order", "sw");
        Platform.startup(()->{});
    }

    @BeforeEach
    void setup() {
        when(toolkit.getScreenSize()).thenReturn(new Dimension());
        sqreenReaderController = new SqreenReaderController(barcodeParser, toolkit, hyperLinkOpener);
    }

    @Test
    @DisplayName("Should get barcode")
    void testGetCurrentBarcode() throws IOException {
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(expectedBarcodeData);
        String barCodeData = sqreenReaderController.getCurrentBarCode();
        assertEquals(expectedBarcodeData, barCodeData);
    }

    @Test
    @DisplayName("Should add barcode to scene")
    void testUpdateSceneWithBarCode() throws IOException, InterruptedException {
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(expectedBarcodeData);
        Label noQRCode = new Label();
        Hyperlink latestQRCode = new Hyperlink();
        sqreenReaderController.setNoQRCode(noQRCode);
        sqreenReaderController.setLatestQRCode(latestQRCode);

        sqreenReaderController.updateSceneWithBarCode();
        TimeUnit.SECONDS.sleep(1);

        assertTrue(latestQRCode.isVisible());
        assertEquals(expectedBarcodeData, latestQRCode.getText());
        assertFalse(noQRCode.isVisible());
    }

    @Test
    @DisplayName("Should not update barcode if null")
    void testNullBarcode() throws IOException, InterruptedException {
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(null);
        Label noQRCode = new Label();
        Hyperlink latestQRCode = new Hyperlink();
        sqreenReaderController.setNoQRCode(noQRCode);
        sqreenReaderController.setLatestQRCode(latestQRCode);

        sqreenReaderController.updateSceneWithBarCode();
        TimeUnit.SECONDS.sleep(1);

        assertTrue(noQRCode.isVisible());
    }

}