package net.sqreenreader.controller;

import net.sqreenreader.service.BarcodeParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SqreenReaderControllerTest {

    @Mock
    private BarcodeParser barcodeParser;

    @Mock
    Toolkit toolkit;

    @Test
    @DisplayName("Should get barcode")
    void testGetCurrentBarcode() throws IOException {
        String expectedBarcodeData = "www.google.com";
        when(barcodeParser.parse(any(Rectangle.class))).thenReturn(expectedBarcodeData);
        when(toolkit.getScreenSize()).thenReturn(new Dimension());
        SqreenReaderController sqreenReaderController = new SqreenReaderController(barcodeParser, toolkit);

        String barCodeData = sqreenReaderController.getCurrentBarCode();

        assertEquals(expectedBarcodeData, barCodeData);
    }

}