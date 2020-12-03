package org.example;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.ExecutionException;

public class SimpleConsumer {
    public static String PULSAR_URL = "{{ pulsar_url }}";
    public static String PULSAR_JWT = "{{ pulsar_url }}";
    public static String PULSAR_TOPIC = "persistent://public/default/java-simple";
    public static String PULSAR_SUBSCRIPTION_NAME = "sample";

    public static void main(String[] argv) {

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .proxyServiceUrl(PULSAR_URL, ProxyProtocol.SNI)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .build()) {
            try (Consumer<byte[]> consumer = client.newConsumer()
                    .topic(PULSAR_TOPIC)
                    .subscriptionName(PULSAR_SUBSCRIPTION_NAME)
                    .subscribe()) {
                Message<byte[]> message = consumer.receive();
                try {
                    System.out.printf("Message Received with Data : %s\n", new String(message.getData()));
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
