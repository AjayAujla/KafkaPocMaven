package com.ajayaujlawork.kafkapocmaven;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//	@Value("${useMicrosoftEmulator}")
//	private boolean useMicrosoftEmulator;

    @Autowired
    private HttpInterceptor httpInterceptor;

    @Autowired
    private MicrosoftEmulatorInterceptor microsoftEmulatorInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry interceptorRegistry) {
//		if (useMicrosoftEmulator) {
        interceptorRegistry.addInterceptor(microsoftEmulatorInterceptor);
//		}
    }

    @Bean
    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(httpInterceptor);
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
