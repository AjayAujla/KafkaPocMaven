package com.ajayaujlawork.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HttpUrlRerouterInterceptor implements ClientHttpRequestInterceptor {

    @Value("${http-request-rerouter.urls}")
    private String[] urlsToReroute;

    @Value("${http-request-rerouter.rerouted-host}")
    private String reroutedHost;

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        log.info("[HttpUrlRerouterInterceptor] Original Request URI: {}", request.getURI());

        final String requestHost = request.getURI().getHost();
        if (Arrays.asList(urlsToReroute).contains(requestHost)) {
            try {
                final URI reroutedUri = new URI(request.getURI().getScheme(), request.getURI().getUserInfo(), reroutedHost, request.getURI().getPort(), request.getURI().getPath(), request.getURI().getQuery(), request.getURI().getFragment());
                log.info("[HttpUrlRerouterInterceptor] Rerouted Request URI: {}", reroutedUri);
                return execution.execute(new HttpRequestUrlRerouterWrapper(request, reroutedUri), body);
            } catch (final URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return execution.execute(request, body);
    }

}
