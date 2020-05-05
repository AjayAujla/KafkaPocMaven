package com.ajayaujlawork.kafkapocmaven.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MicrosoftTenantEventBinding {

    String MICROSOFT_TENANT_TOPIC = "microsoft-tenant";

    @Input(MICROSOFT_TENANT_TOPIC)
    MessageChannel input();

    @Output(MICROSOFT_TENANT_TOPIC)
    MessageChannel output();
}
