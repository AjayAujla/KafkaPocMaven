package com.appdirect.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Bindings {

    String KAFKA_POC_MAVEN_INPUT = "kafkaPocMavenInput";
    String KAFKA_POC_MAVEN_OUTPUT = "kafkaPocMavenOutput";

    @Input(KAFKA_POC_MAVEN_INPUT)
    MessageChannel input();

    @Output(KAFKA_POC_MAVEN_OUTPUT)
    MessageChannel output();
}
