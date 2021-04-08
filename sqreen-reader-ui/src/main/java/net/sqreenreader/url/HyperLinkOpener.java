package net.sqreenreader.url;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class HyperLinkOpener {

    private final Desktop desktop;

    public HyperLinkOpener(final Desktop desktop) {
        this.desktop = desktop;
    }

    public void open(final String url) throws IOException, URISyntaxException {
        try {
            URL urlToOpen = UrlBuilder.build(url);
            desktop.browse(urlToOpen.toURI());
        } catch (MalformedURLException ignored) {
        }
    }
}
