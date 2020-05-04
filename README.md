# Kafka POC Maven

## Setup

### Generate Java Classes from Avro Schema

`avro-maven-plugin:1.8.2:schema` has been included as a maven plugin. 

Perform `mvn clean compile` to execute it.

## Project Guide

##### src/main/resources/*-schema.avsc

Avro schema file directory. 

Specified in `pom.xml` as a `sourceDirectory` for the `avro-maven-plugin:1.8.2:schema` plugin.

##### application.yml

Specifies topics

```yml
spring:
  cloud:
    stream:
      bindings:

        TopicName1:
          destination: TOPIC.NAME.1

        TopicName2:
          destination: TOPIC.NAME.2
```

Refer to `TopicName*` in a `*Bindings.java` interface 

##### *Bindings.java

```java
public interface TopicName1Bindings {

    String TOPIC_NAME_1 = "TopicName1";

    @Input(TOPIC_NAME_1)
    MessageChannel input();

    @Output(TOPIC_NAME_1)
    MessageChannel output();
}
```

##### Application.java

```java
@SpringBootApplication
@EnableBinding({TopicName1Bindings.class})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
