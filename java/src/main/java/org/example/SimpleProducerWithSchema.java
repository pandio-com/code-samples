package org.example;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.client.api.schema.RecordSchemaBuilder;
import org.apache.pulsar.client.api.schema.SchemaBuilder;
import org.apache.pulsar.client.impl.schema.generic.GenericAvroSchema;
import org.apache.pulsar.common.schema.SchemaInfo;
import org.apache.pulsar.common.schema.SchemaType;

import java.util.Random;

public class SimpleProducerWithSchema {
    public static String PULSAR_URL = "{{ pulsar_url }}";
    public static String PULSAR_JWT = "{{ pulsar_jwt }}";
    public static String PULSAR_TOPIC = "persistent://{{pulsar_tenant}}/{{pulsar_namespace}}/java-simple-with-schema";

    
    public static void main(String[] argv) throws PulsarClientException {
        Random rand = new Random();
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .proxyServiceUrl(PULSAR_URL, ProxyProtocol.SNI)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .build();

        RecordSchemaBuilder recordSchemaBuilder = SchemaBuilder.record("user");
        recordSchemaBuilder.field("name").type(SchemaType.STRING);
        recordSchemaBuilder.field("age").type(SchemaType.INT32);
        recordSchemaBuilder.field("last_name").type(SchemaType.STRING);
        SchemaInfo schemaInfo = recordSchemaBuilder.build(SchemaType.AVRO);
        
        Producer<GenericRecord> producer = client.newProducer(Schema.generic(schemaInfo))
                .topic(PULSAR_TOPIC)
                .create();

        GenericAvroSchema schema = new GenericAvroSchema(schemaInfo);

        MessageId msgId = producer.newMessage().value(schema.newRecordBuilder()
                .set("name", "Foo")
                .set("age", rand.nextInt(75))
                .set("last_name", "Bar")
                .build())
                .send();

        System.out.println("Message Published: " + msgId);
        producer.close();
        client.close();
    }
}