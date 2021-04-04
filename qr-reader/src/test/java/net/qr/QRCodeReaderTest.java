package net.qr;


import com.google.zxing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QRCodeReaderTest {

    @Mock
    private Reader reader;

    private QRCodeReader qrCodeReader;

    @BeforeEach
    void setup() {
        qrCodeReader = new QRCodeReader(reader);
    }

    @Test
    @DisplayName("Should read QRCodes")
    void testReadQRCode() throws NotFoundException, FormatException, ChecksumException, IOException {
        String expectedUrl = "http//localhost:8080";
        Result result = new Result(expectedUrl, null, null, null);
        when(reader.decode(any(BinaryBitmap.class))).thenReturn(result);

        String qrCodeReadResult = qrCodeReader.read(ImageIO.read(getClass().getResourceAsStream("/test.jpg")));

        assertEquals(expectedUrl, qrCodeReadResult);
    }

    @Test
    @DisplayName("Should throw error if failed to parse QR Code")
    void testFailure() throws FormatException, ChecksumException, NotFoundException, IOException {
        when(reader.decode(any(BinaryBitmap.class))).thenThrow(FormatException.class);
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/test.jpg"));
        assertThrows(IOException.class, () -> {
            qrCodeReader.read(image);
        });
    }

}