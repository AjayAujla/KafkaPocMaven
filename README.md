# Kafka POC Maven

## Setup

### Generate Java Classes from Avro Schema

`avro-maven-plugin:1.8.2:schema` has been included as a maven plugin. 

Perform `mvn clean compile` to execute it.

## Running

`POST` `http://localhost:8080/publish/1`

```
Publishing MicrosoftTenantEvent={"user_id": "userId", "sales_agent_user_id": "salesAgentUserId", "partner": "partner", "sku": "sku", "event_type": "CREATED", "company_id": "companyId", "external_company_id": "externalCompanyId", "batch_id": "batchId", "customer_id": "customerId", "sales_agent_customer_id": "salesAgentCustomerId", "tenant_domain": "tenantDomain", "creation_date_time": 1588600243171, "marketplace_order_number": 1, "tenant_admin_email": "tenantAdminEmail", "purchaser_name": "purchaserName"}
Received MicrosoftTenantEvent={"user_id": "userId", "sales_agent_user_id": "salesAgentUserId", "partner": "partner", "sku": "sku", "event_type": "CREATED", "company_id": "companyId", "external_company_id": "externalCompanyId", "batch_id": "batchId", "customer_id": "customerId", "sales_agent_customer_id": "salesAgentCustomerId", "tenant_domain": "tenantDomain", "creation_date_time": 1588600243171, "marketplace_order_number": 1, "tenant_admin_email": "tenantAdminEmail", "purchaser_name": "purchaserName"}
```

`POST` `http://localhost:8080/publish/2`

```
Publishing CspTokenEvent={"creation_date_time": 1588600246150, "expiration_date_time": 1588600246150, "authorizer": "authorizer", "tenant_domain": "tenantDomain", "event_type": "CREATED"}
Received CspTokenEvent={"creation_date_time": 1588600246150, "expiration_date_time": 1588600246150, "authorizer": "authorizer", "tenant_domain": "tenantDomain", "event_type": "CREATED"}
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

https://github.com/AppDirect/usage-events/blob/d431155956a58995e8584f1a56bbca181e019d58/avro-schema/readme.md

https://github.com/AppDirect/fusion_poc/tree/master/wg-eda/schema-registry/java
