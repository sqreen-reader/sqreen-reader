package net.sqreenreader.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BarcodeParserServiceTest {

    @Test
    @DisplayName("Should construct")
    void testConstructs() {
        assertTrue(new BarcodeParserService() instanceof  ImageParser);
    }

}