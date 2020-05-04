package com.ajayaujlawork.kafkapocmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.ajayaujlawork.kafkapocmaven.model.CspTokenEventBindings;
import com.ajayaujlawork.kafkapocmaven.model.MicrosoftTenantEventBindings;

@SpringBootApplication
@EnableBinding({CspTokenEventBindings.class, MicrosoftTenantEventBindings.class})
public class KafkaPocMavenApplication {

    public static void main(final String[] args) {
        SpringApplication.run(KafkaPocMavenApplication.class, args);
    }

}