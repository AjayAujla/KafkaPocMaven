package com.appdirect.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.appdirect.kafkapocmaven.model.CspTokenEvent;
import com.appdirect.kafkapocmaven.model.CspTokenEventBindings;
import com.appdirect.kafkapocmaven.service.SerializationService;

@Slf4j
@Component
public class CspTokenEventSubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(CspTokenEventBindings.KAFKA_POC_MAVEN_TOPIC_CSP_TOKEN_EVENT)
    public void subscribe(final byte[] body) {
        final CspTokenEvent event = serializationService.deserialize(body, CspTokenEvent.class);
        log.info("AJAY Received CspTokenEvent={}", event);
    }
}
