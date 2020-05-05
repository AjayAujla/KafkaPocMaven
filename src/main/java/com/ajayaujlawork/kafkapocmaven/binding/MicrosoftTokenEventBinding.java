package com.ajayaujlawork.kafkapocmaven.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MicrosoftTokenEventBinding {

    String MICROSOFT_TOKEN_TOPIC = "microsoft-token";

    @Input(MICROSOFT_TOKEN_TOPIC)
    MessageChannel input();

    @Output(MICROSOFT_TOKEN_TOPIC)
    MessageChannel output();
}
