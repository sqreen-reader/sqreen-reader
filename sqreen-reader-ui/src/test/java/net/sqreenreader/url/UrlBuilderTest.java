package net.sqreenreader.url;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static net.sqreenreader.url.UrlBuilder.DEFAULT_PROTOCOL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UrlBuilderTest {

    @Test
    @DisplayName("Should throw error if not a valid url")
    void testInvalidUrl() {
        String invalidUrl = "not a url";
        assertThrows(MalformedURLException.class, () -> UrlBuilder.build(invalidUrl));
    }

    @Test
    @DisplayName("Should build a valid url")
    void testValidUrl() throws MalformedURLException {
        String validUrl = "http://www.google.com";
        URL url = UrlBuilder.build(validUrl);
        assertEquals(validUrl, url.toString());
    }

    @Test
    @DisplayName("Should add default protocol")
    void testAddDefaultProtocol() throws MalformedURLException {
        String validUrl = "www.google.com";
        URL url = UrlBuilder.build(validUrl);
        assertEquals(DEFAULT_PROTOCOL+validUrl, url.toString());
    }
}