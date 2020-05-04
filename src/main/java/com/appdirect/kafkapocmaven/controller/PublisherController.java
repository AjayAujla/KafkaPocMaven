package com.appdirect.kafkapocmaven.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.kafkapocmaven.model.Bindings;
import com.appdirect.kafkapocmaven.model.CspTokenEvent;
import com.appdirect.kafkapocmaven.model.CspTokenEventType;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEvent;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEventType;
import com.appdirect.kafkapocmaven.service.SerializationService;

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private Bindings bindings;

    @Autowired
    private SerializationService serializationService;
    
    @PostMapping
    public ResponseEntity publish() {
//        final MicrosoftTenantEvent event = new MicrosoftTenantEvent("userId", "salesAgentUserId", "partner", "sku", MicrosoftTenantEventType.CREATED, "companyId", "externalCompanyId", "batchId", "customerId", "salesAgentCustomerId", "tenantDomain", "creationDateTime", 1, "tenantAdminEmail", "purchaserName");
        final CspTokenEvent event = new CspTokenEvent("creationDateTime", "expirationDateTime", "authorizer", "tenantDomain", CspTokenEventType.CREATED);
        final byte[] serialized = serializationService.serialize(event, CspTokenEvent.class);
        final Message<byte[]> message = MessageBuilder.withPayload(serialized).build();
        
        log.info("AJAY Publishing event={}", event);
        bindings.output().send(message);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> pingo() {
        return ResponseEntity.ok("pongo");
    }
}
