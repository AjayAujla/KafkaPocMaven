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

import com.appdirect.kafkapocmaven.model.CspTokenEvent;
import com.appdirect.kafkapocmaven.model.CspTokenEventBindings;
import com.appdirect.kafkapocmaven.model.CspTokenEventType;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEvent;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEventBindings;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEventType;
import com.appdirect.kafkapocmaven.service.SerializationService;

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private CspTokenEventBindings cspTokenEventBindings;

    @Autowired
    private MicrosoftTenantEventBindings microsoftTenantEventBindings;

    @Autowired
    private SerializationService serializationService;

    @PostMapping("/1")
    public ResponseEntity publish1() {
        final MicrosoftTenantEvent event = MicrosoftTenantEvent.newBuilder()
                .setUserId("userId")
                .setSalesAgentUserId("salesAgentUserId")
                .setPartner("partner")
                .setSku("sku")
                .setEventType(MicrosoftTenantEventType.CREATED)
                .setCompanyId("companyId")
                .setExternalCompanyId("externalCompanyId")
                .setBatchId("batchId")
                .setCustomerId("customerId")
                .setSalesAgentCustomerId("salesAgentCustomerId")
                .setTenantDomain("tenantDomain")
                .setCreationDateTime("creationDateTime")
                .setMarketplaceOrderNumber(1)
                .setTenantAdminEmail("tenantAdminEmail")
                .setPurchaserName("purchaserName")
                .build();
        final byte[] serialized = serializationService.serialize(event, MicrosoftTenantEvent.class);
        final Message<byte[]> message = MessageBuilder.withPayload(serialized).build();

        log.info("AJAY Publishing MicrosoftTenantEvent={}", event);
        microsoftTenantEventBindings.output().send(message);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/2")
    public ResponseEntity publish2() {
        final CspTokenEvent event = CspTokenEvent.newBuilder()
                .setCreationDateTime("creationDateTime")
                .setExpirationDateTime("expirationDateTime")
                .setAuthorizer("authorizer")
                .setTenantDomain("tenantDomain")
                .setEventType(CspTokenEventType.CREATED)
                .build();
        final byte[] serialized = serializationService.serialize(event, CspTokenEvent.class);
        final Message<byte[]> message = MessageBuilder.withPayload(serialized).build();

        log.info("AJAY Publishing CspTokenEvent={}", event);
        cspTokenEventBindings.output().send(message);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> pingo() {
        return ResponseEntity.ok("pongo");
    }
}
