package net.qr;


import com.google.zxing.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.imageio.ImageIO;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QRCodeReaderTest {

    @Mock
    private Reader reader;

    @Test
    @DisplayName("Should read QRCodes")
    void testReadQRCode() throws NotFoundException, FormatException, ChecksumException, IOException {
        String expectedUrl = "http//localhost:8080";
        Result result = new Result(expectedUrl, null, null, null);
        when(reader.decode(any(BinaryBitmap.class))).thenReturn(result);

        QRCodeReader qrCodeReader = new QRCodeReader(reader);
        String qrCodeReadResult = qrCodeReader.read(ImageIO.read(getClass().getResourceAsStream("/test.jpg")));

        assertEquals(expectedUrl, qrCodeReadResult);
    }

}