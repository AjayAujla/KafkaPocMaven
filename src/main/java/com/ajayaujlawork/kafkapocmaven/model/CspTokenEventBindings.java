package com.ajayaujlawork.kafkapocmaven.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CspTokenEventBindings {

    String TOPIC_CSP_TOKEN_EVENT = "Topic-CspTokenEvent";

    @Input(TOPIC_CSP_TOKEN_EVENT)
    MessageChannel input();

    @Output(TOPIC_CSP_TOKEN_EVENT)
    MessageChannel output();
}
