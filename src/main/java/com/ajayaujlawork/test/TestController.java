package com.ajayaujlawork.test;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/test")
    public ResponseEntity test() {
        log.info("AJAY handle /test");
        
        final String result = restTemplate.getForObject("https://google.ca/search?q=hallo", String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
