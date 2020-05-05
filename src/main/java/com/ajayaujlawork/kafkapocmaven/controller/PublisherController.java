package com.ajayaujlawork.kafkapocmaven.controller;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEvent;
import com.ajayaujlawork.kafkapocmaven.binding.MicrosoftTenantEventBinding;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEventType;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTokenEvent;
import com.ajayaujlawork.kafkapocmaven.binding.MicrosoftTokenEventBinding;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTokenEventType;
import com.ajayaujlawork.kafkapocmaven.service.SerializationService;

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private MicrosoftTokenEventBinding microsoftTokenEventBinding;

    @Autowired
    private MicrosoftTenantEventBinding microsoftTenantEventBinding;

    @Autowired
    private SerializationService serializationService;

    @PostMapping("/tenant")
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
                .setCreationDateTime(new Date().getTime())
                .setMarketplaceOrderNumber(1)
                .setTenantAdminEmail("tenantAdminEmail")
                .setPurchaserName("purchaserName")
                .build();
        final byte[] serialized = serializationService.serialize(event, MicrosoftTenantEvent.class);
        final Message<byte[]> message = MessageBuilder.withPayload(serialized).build();

        log.info("AJAY Publishing MicrosoftTenantEvent={}", event);
        microsoftTenantEventBinding.output().send(message);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/token")
    public ResponseEntity publish2() {
        final MicrosoftTokenEvent event = MicrosoftTokenEvent.newBuilder()
                .setCreationDateTime(new Date().getTime())
                .setExpirationDateTime(new Date().getTime())
                .setAuthorizer("authorizer")
                .setTenantDomain("tenantDomain")
                .setEventType(MicrosoftTokenEventType.CREATED)
                .build();
        final byte[] serialized = serializationService.serialize(event, MicrosoftTokenEvent.class);
        final Message<byte[]> message = MessageBuilder.withPayload(serialized).build();

        log.info("AJAY Publishing MicrosoftTokenEvent={}", event);
        microsoftTokenEventBinding.output().send(message);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
