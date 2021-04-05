package net.sqreenreader.service;


import net.qr.BarCodeReader;
import net.screen.ImageGrabber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BarcodeParserServiceTest {

    @Mock
    private ImageGrabber imageGrabber;

    @Mock
    private BarCodeReader barCodeReader;


    @Test
    @DisplayName("Should parse Barcode")
    void  testParse() throws IOException {
        String expectedParsedBarcode = "http://localhost:8080";
        when(imageGrabber.grab(any(Rectangle.class))).thenReturn(ImageIO.read(getClass().getResourceAsStream("/test.jpg")));
        when(barCodeReader.read(any(BufferedImage.class))).thenReturn(expectedParsedBarcode);

        BarcodeParserService barcodeParserService = new BarcodeParserService(imageGrabber, barCodeReader);
        String parseBarcode = barcodeParserService.parse(new Rectangle());

        assertEquals(expectedParsedBarcode, parseBarcode);
    }

}