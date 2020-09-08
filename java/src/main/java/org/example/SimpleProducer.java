package org.example;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class SimpleProducer {
    public static String PULSAR_URL = "";
    public static String PULSAR_JWT = "";
    public static String PULSAR_TOPIC = "persistent://pandio/pandio-service/test";
    public static String PULSAR_SUBSCRIPTION_NAME = "sample-test";

    public static void main(String[] argv) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .allowTlsInsecureConnection(true)
                .build();
        Producer<byte[]> producer = client.newProducer()
                .topic(PULSAR_TOPIC)
                .create();
        producer.send("Test Message".getBytes());

    }
}
