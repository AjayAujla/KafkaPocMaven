package com.appdirect.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Bindings {

    String KAFKA_POC_MAVEN_TOPIC = "kafkaPocMavenTopic";

    @Input(KAFKA_POC_MAVEN_TOPIC)
    MessageChannel input();

    @Output(KAFKA_POC_MAVEN_TOPIC)
    MessageChannel output();
}
