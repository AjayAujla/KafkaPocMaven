package com.ajayaujlawork.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEventBindings;
import com.ajayaujlawork.kafkapocmaven.service.SerializationService;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEvent;

@Slf4j
@Component
public class MicrosoftTenantEventSubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(MicrosoftTenantEventBindings.TOPIC_MICROSOFT_TENANT_EVENT)
    public void subscribe(final byte[] payload) {
        final MicrosoftTenantEvent event = serializationService.deserialize(payload, MicrosoftTenantEvent.class);
        log.info("AJAY Received MicrosoftTenantEvent={}", event);
    }
}
