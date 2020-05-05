package com.ajayaujlawork.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEvent;
import com.ajayaujlawork.kafkapocmaven.binding.MicrosoftTenantEventBinding;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTokenEvent;
import com.ajayaujlawork.kafkapocmaven.binding.MicrosoftTokenEventBinding;
import com.ajayaujlawork.kafkapocmaven.service.SerializationService;

@Slf4j
@Component
public class SubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(MicrosoftTenantEventBinding.MICROSOFT_TENANT_TOPIC)
    public void handleMicrosoftTenantEvent(final byte[] payload) {
        final MicrosoftTenantEvent event = serializationService.deserialize(payload, MicrosoftTenantEvent.class);
        log.info("AJAY Received MicrosoftTenantEvent={}", event);
    }

    @StreamListener(MicrosoftTokenEventBinding.MICROSOFT_TOKEN_TOPIC)
    public void handleMicrosoftTokenEvent(final byte[] payload) {
        final MicrosoftTokenEvent event = serializationService.deserialize(payload, MicrosoftTokenEvent.class);
        log.info("AJAY Received MicrosoftTokenEvent={}", event);
    }
}
