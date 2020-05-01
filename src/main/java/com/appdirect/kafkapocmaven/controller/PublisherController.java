package com.appdirect.kafkapocmaven.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.kafkapocmaven.model.Bindings;
import com.appdirect.kafkapocmaven.model.TopicBody;

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private Bindings bindings;

    @PostMapping
    public ResponseEntity publish(@RequestBody final TopicBody topicBody) {
        log.info("AJAY Publishing topicBody={}", topicBody);
        final Message<TopicBody> message = MessageBuilder.withPayload(topicBody).build();
        bindings.output().send(message);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> pingo() {
        return ResponseEntity.ok("pongo");
    }
}
