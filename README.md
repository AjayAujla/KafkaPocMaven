# Kafka POC Maven

## Setup

### Generate Java Classes from Avro Schema

`avro-maven-plugin:1.8.2:schema` has been included as a maven plugin. 

Perform `mvn clean compile` to execute it.

### Schema Registry

```
brew install kafka
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
kafka-console-producer --broker-list localhost:9092 --topic test
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```

```
export CONFLUENT_HOME=/Users/ajay.aujla/Documents/repos/confluent-5.5.0
confluent local destroy
confluent local stop
confluent local start
```

- Setup scheme registry in the application.
- Run project
- View Confluent / Schema Registry data at the following endpoints:
    - http://localhost:8081/subjects
    - http://localhost:9021/clusters

## Running

`POST` `http://localhost:8080/publish/1`

```
Publishing MicrosoftTenantEvent={"user_id": "userId", "sales_agent_user_id": "salesAgentUserId", "partner": "partner", "sku": "sku", "event_type": "CREATED", "company_id": "companyId", "external_company_id": "externalCompanyId", "batch_id": "batchId", "customer_id": "customerId", "sales_agent_customer_id": "salesAgentCustomerId", "tenant_domain": "tenantDomain", "creation_date_time": 1588600243171, "marketplace_order_number": 1, "tenant_admin_email": "tenantAdminEmail", "purchaser_name": "purchaserName"}
Received MicrosoftTenantEvent={"user_id": "userId", "sales_agent_user_id": "salesAgentUserId", "partner": "partner", "sku": "sku", "event_type": "CREATED", "company_id": "companyId", "external_company_id": "externalCompanyId", "batch_id": "batchId", "customer_id": "customerId", "sales_agent_customer_id": "salesAgentCustomerId", "tenant_domain": "tenantDomain", "creation_date_time": 1588600243171, "marketplace_order_number": 1, "tenant_admin_email": "tenantAdminEmail", "purchaser_name": "purchaserName"}
```

`POST` `http://localhost:8080/publish/2`

```
Publishing MicrosoftTokenEvent={"creation_date_time": 1588600246150, "expiration_date_time": 1588600246150, "authorizer": "authorizer", "tenant_domain": "tenantDomain", "event_type": "CREATED"}
Received MicrosoftTokenEvent={"creation_date_time": 1588600246150, "expiration_date_time": 1588600246150, "authorizer": "authorizer", "tenant_domain": "tenantDomain", "event_type": "CREATED"}
```

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

# References

- https://github.com/AppDirect/usage-events/blob/d431155956a58995e8584f1a56bbca181e019d58/avro-schema/readme.md
- https://github.com/AppDirect/fusion_poc/tree/master/wg-eda/schema-registry/java
