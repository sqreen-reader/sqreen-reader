package net.sqreenreader.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class UrlBuilder {

    public static final String DEFAULT_PROTOCOL = "https://";

    public static URL build(final String url) throws MalformedURLException {
        validateUri(url);
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            return urlWithDefaultProtocol(url);
        }
    }

    private static void validateUri(final String url) throws MalformedURLException {
        try {
            new URI(url);
        } catch (URISyntaxException e) {
            throw new MalformedURLException(e.getMessage());
        }
    }

    private static URL urlWithDefaultProtocol(final String url) throws MalformedURLException {
        return new URL(DEFAULT_PROTOCOL + url);
    }

    private UrlBuilder() {
    }
}
