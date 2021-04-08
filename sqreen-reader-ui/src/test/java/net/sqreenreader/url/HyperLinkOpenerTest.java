package net.sqreenreader.url;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HyperLinkOpenerTest {

    @Mock
    Desktop desktop;

    @Test
    @DisplayName("Should open links")
    void testOpen() throws IOException, URISyntaxException {
        String url = "https://google.com";
        HyperLinkOpener hyperLinkOpener = new HyperLinkOpener(desktop);
        hyperLinkOpener.open(url);
        verify(desktop, times(1)).browse(new URI(url));
    }

    @Test
    @DisplayName("Should not open invalid urls")
    void testInvalidUrls() throws IOException, URISyntaxException {
        String url = "not a url";
        HyperLinkOpener hyperLinkOpener = new HyperLinkOpener(desktop);
        hyperLinkOpener.open(url);
        verify(desktop, never()).browse(any(URI.class));
    }
}