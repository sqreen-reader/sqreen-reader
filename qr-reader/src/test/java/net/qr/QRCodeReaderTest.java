package net.qr;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QRCodeReaderTest {

    @Test
    @DisplayName("Should be constructed")
    void testConstruct() {
        assertTrue(new QRCodeReader() instanceof BarCodeReader);
    }

}