package com.ajayaujlawork.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

public class HttpRequestUrlRerouterWrapper extends HttpRequestWrapper {
    private final URI reroutedURI;

    HttpRequestUrlRerouterWrapper(final HttpRequest request, final URI uri) throws URISyntaxException {
        super(request);
        reroutedURI = new URI(uri.toString());
    }

    @Override
    public URI getURI() {
        return reroutedURI;
    }
}
