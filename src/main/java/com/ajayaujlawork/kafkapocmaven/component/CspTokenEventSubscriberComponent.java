package com.ajayaujlawork.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.ajayaujlawork.kafkapocmaven.model.CspTokenEvent;
import com.ajayaujlawork.kafkapocmaven.model.CspTokenEventBindings;
import com.ajayaujlawork.kafkapocmaven.service.SerializationService;

@Slf4j
@Component
public class CspTokenEventSubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(CspTokenEventBindings.TOPIC_CSP_TOKEN_EVENT)
    public void subscribe(final byte[] payload) {
        final CspTokenEvent event = serializationService.deserialize(payload, CspTokenEvent.class);
        log.info("AJAY Received CspTokenEvent={}", event);
    }
}
