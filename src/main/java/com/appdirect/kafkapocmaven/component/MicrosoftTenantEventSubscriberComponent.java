package com.appdirect.kafkapocmaven.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.appdirect.kafkapocmaven.model.MicrosoftTenantEvent;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEventBindings;
import com.appdirect.kafkapocmaven.service.SerializationService;

@Slf4j
@Component
public class MicrosoftTenantEventSubscriberComponent {

    @Autowired
    private SerializationService serializationService;

    @StreamListener(MicrosoftTenantEventBindings.TOPIC_MICROSOFT_TENANT_EVENT)
    public void subscribe(final byte[] body) {
        final MicrosoftTenantEvent event = serializationService.deserialize(body, MicrosoftTenantEvent.class);
        log.info("AJAY Received MicrosoftTenantEvent={}", event);
    }
}
