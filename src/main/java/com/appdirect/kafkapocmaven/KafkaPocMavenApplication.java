package com.appdirect.kafkapocmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.appdirect.kafkapocmaven.model.Bindings;

@EnableBinding({Bindings.class})
@SpringBootApplication
public class KafkaPocMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaPocMavenApplication.class, args);
    }

}
