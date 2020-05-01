package com.appdirect.kafkapocmaven.subscriber;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.appdirect.kafkapocmaven.model.Bindings;
import com.appdirect.kafkapocmaven.model.TopicBody;

@Slf4j
@Component
public class SubscriberComponent {

    @StreamListener(Bindings.KAFKA_POC_MAVEN_TOPIC)
    public void subscribe(final TopicBody topicBody) {
        log.info("AJAY Received topicBody={}", topicBody);
    }
}
