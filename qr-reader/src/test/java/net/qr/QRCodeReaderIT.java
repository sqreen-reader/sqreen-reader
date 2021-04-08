package net.qr;

import com.google.zxing.MultiFormatReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QRCodeReaderIT {

    @Test
    @DisplayName("Should read QR Code")
    void testReadCleanQRCode() throws IOException {
        QRCodeReader qrCodeReader = new QRCodeReader(new MultiFormatReader());
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/test.jpg"));
        Barcode barcode = qrCodeReader.read(image);

        assertEquals("https://www.geeksforgeeks.org", barcode.getText());
    }

    @Test
    @DisplayName("Should read image that has more than just the QR code")
    void testReadDirtyRCode() throws IOException {
        QRCodeReader qrCodeReader = new QRCodeReader(new MultiFormatReader());
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/dirty-qr.png"));
        Barcode barcode = qrCodeReader.read(image);

        assertEquals("https://www.google.com/", barcode.getText());
    }
}
