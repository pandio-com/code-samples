package org.example.helloworld;

import org.apache.pulsar.client.api.*;

public class SimpleProducer {
    public static String PULSAR_URL = "{{ pulsar_url }}";
    public static String PULSAR_JWT = "{{ pulsar_jwt }}";
    public static String PULSAR_TOPIC = "persistent://{{pulsar_tenant}}/{{pulsar_namespace}}/java-simple";
    
    public static void main(String[] argv) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .proxyServiceUrl(PULSAR_URL, ProxyProtocol.SNI)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic(PULSAR_TOPIC)
                .create();

        MessageId msgId = producer.send("Test Message".getBytes());

        System.out.println("Message Published: " + msgId);
        producer.close();
        client.close();
    }
}
