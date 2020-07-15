package com.ajayaujlawork.kafkapocmaven;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HttpInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
//        return execution.execute(new MyHttpRequestWrapper(request), body);
        
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        log.info("===========================Request begin======================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.info("==========================Request end=========================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.info("============================Response begin====================================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        log.info("=======================Response end===========================================");
    }

    private class MyHttpRequestWrapper extends HttpRequestWrapper {
        MyHttpRequestWrapper(HttpRequest request) {
            super(request);
        }

        @Override
        public URI getURI() {
            try {
                return new URI("https://github.com/AppDirect");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
