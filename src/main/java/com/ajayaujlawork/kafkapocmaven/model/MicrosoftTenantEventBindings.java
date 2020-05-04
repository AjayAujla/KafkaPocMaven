package com.appdirect.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MicrosoftTenantEventBindings {

    String TOPIC_MICROSOFT_TENANT_EVENT = "Topic-MicrosoftTenantEvent";

    @Input(TOPIC_MICROSOFT_TENANT_EVENT)
    MessageChannel input();

    @Output(TOPIC_MICROSOFT_TENANT_EVENT)
    MessageChannel output();
}
