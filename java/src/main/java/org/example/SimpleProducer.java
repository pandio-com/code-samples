package org.example;

import org.apache.pulsar.client.api.*;

public class SimpleProducer {
    public static String PULSAR_URL = "";
    public static String PULSAR_JWT = "";
    public static String PULSAR_TOPIC = "persistent://pandio/pandio-service/test";
    public static String PULSAR_TOPIC = "producer-consumer-test";

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
