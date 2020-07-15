package com.ajayaujlawork.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    @Value("${http-request-rerouter.enabled}")
    private Boolean isHttpRequestRerouterEnabled;

    @Value("${http-request-rerouter.urls}")
    private String[] urlsToReroute;

    @Value("${http-request-rerouter.rerouted-host}")
    private String reroutedHost;

    @Autowired
    private HttpUrlRerouterInterceptor httpUrlRerouterInterceptor;

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        
        if(isHttpRequestRerouterEnabled) {
            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            if (CollectionUtils.isEmpty(interceptors)) {
                interceptors = new ArrayList<>();
            }
            interceptors.add(httpUrlRerouterInterceptor);
            restTemplate.setInterceptors(interceptors);
        }

        return restTemplate;
    }
}
