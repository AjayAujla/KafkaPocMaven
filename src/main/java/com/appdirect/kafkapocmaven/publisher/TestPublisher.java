package com.appdirect.kafkapocmaven.publisher;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.kafkapocmaven.model.Bindings;
import com.appdirect.kafkapocmaven.model.Message;

@Slf4j
@RestController
@RequestMapping("/publish")
public class TestPublisher {

    @Autowired
    private Bindings bindings;

    @PostMapping
    public ResponseEntity publish(@RequestBody final Message message) {
        log.info("AJAY Publishing message={}", message);
        bindings.output().send(MessageBuilder.withPayload(message).build());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<String> pingo() {
        return ResponseEntity.ok("pongo");
    }
}
