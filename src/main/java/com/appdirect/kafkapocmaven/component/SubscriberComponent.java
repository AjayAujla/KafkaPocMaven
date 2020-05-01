package com.appdirect.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.appdirect.kafkapocmaven.model.Bindings;
import com.appdirect.kafkapocmaven.model.CspTokenEvent;
import com.appdirect.kafkapocmaven.service.SerializationService;

@Slf4j
@Component
public class SubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(Bindings.KAFKA_POC_MAVEN_TOPIC)
    public void subscribe(final byte[] body) {
//        final MicrosoftTenantEvent event = serializationService.deserialize1(body);
        final CspTokenEvent event = serializationService.deserialize2(body);
        log.info("AJAY Received event={}", event);
    }
}
