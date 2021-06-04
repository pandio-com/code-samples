package org.example;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.api.schema.RecordSchemaBuilder;
import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.client.api.schema.SchemaBuilder;
import org.apache.pulsar.common.schema.SchemaInfo;
import org.apache.pulsar.common.schema.SchemaType;
import org.apache.pulsar.client.impl.schema.AvroSchema;

import java.util.concurrent.ExecutionException;

public class SimpleConsumerWithSchema {
    public static String PULSAR_URL = "{{ pulsar_url }}";
    public static String PULSAR_JWT = "{{ pulsar_jwt }}";
    public static String PULSAR_TOPIC = "persistent://{{pulsar_tenant}}/{{pulsar_namespace}}/java-simple-with-schema";
    public static String PULSAR_SUBSCRIPTION_NAME = "sample";

    public static void main(String[] argv) {

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .proxyServiceUrl(PULSAR_URL, ProxyProtocol.SNI)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .build()) {
                    RecordSchemaBuilder recordSchemaBuilder = SchemaBuilder.record("user");
                    recordSchemaBuilder.field("name").type(SchemaType.STRING);
                    recordSchemaBuilder.field("age").type(SchemaType.INT32);
                    recordSchemaBuilder.field("last_name").type(SchemaType.STRING);
                    SchemaInfo schemaInfo = recordSchemaBuilder.build(SchemaType.AVRO);
            try (Consumer<GenericRecord> consumer = client.newConsumer(Schema.generic(schemaInfo))
                    .topic(PULSAR_TOPIC)
                    .subscriptionName(PULSAR_SUBSCRIPTION_NAME)
                    .subscribe()) {
                Message<GenericRecord> message = consumer.receive();
                try {
                    GenericRecord gr = message.getValue();
                    System.out.printf("Message Received with Data : %s\n", gr.getField("name"));
                    System.out.printf("Message Received with Data : %s\n", gr.getField("age"));
                    System.out.printf("Message Received with Data : %s\n", gr.getField("last_name"));
                    consumer.acknowledge(message);
                } catch (Exception e ) {
                    consumer.negativeAcknowledge(message);
                    e.printStackTrace();
                } finally {
                    consumer.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
