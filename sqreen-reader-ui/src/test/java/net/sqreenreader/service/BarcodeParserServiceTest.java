package net.sqreenreader.service;


import net.qr.BarCodeReader;
import net.qr.Barcode;
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
        BufferedImage expectedImage = ImageIO.read(getClass().getResourceAsStream("/test.jpg"));
        Barcode expectedParsedBarcode = new Barcode("http://localhost:8080", expectedImage);
        when(imageGrabber.grab(any(Rectangle.class))).thenReturn(expectedImage);
        when(barCodeReader.read(any(BufferedImage.class))).thenReturn(expectedParsedBarcode);

        BarcodeParserService barcodeParserService = new BarcodeParserService(imageGrabber, barCodeReader);
        Barcode barcode = barcodeParserService.parse(new Rectangle());

        assertEquals(expectedParsedBarcode, barcode);
    }

}