package com.appdirect.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CspTokenEventBindings {

    String KAFKA_POC_MAVEN_TOPIC_CSP_TOKEN_EVENT = "kafkaPocMavenTopicCspTokenEvent";

    @Input(KAFKA_POC_MAVEN_TOPIC_CSP_TOKEN_EVENT)
    MessageChannel input();

    @Output(KAFKA_POC_MAVEN_TOPIC_CSP_TOKEN_EVENT)
    MessageChannel output();
}
