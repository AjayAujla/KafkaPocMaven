package com.appdirect.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MicrosoftTenantEventBindings {

    String KAFKA_POC_MAVEN_TOPIC_MICROSOFT_TENANT_EVENT = "kafkaPocMavenTopicMicrosoftTenantEvent";

    @Input(KAFKA_POC_MAVEN_TOPIC_MICROSOFT_TENANT_EVENT)
    MessageChannel input();

    @Output(KAFKA_POC_MAVEN_TOPIC_MICROSOFT_TENANT_EVENT)
    MessageChannel output();
}
