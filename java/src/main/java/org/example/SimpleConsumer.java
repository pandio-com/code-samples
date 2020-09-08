package org.example;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.ExecutionException;

public class SimpleConsumer {
    public static String PULSAR_URL = "";
    public static String PULSAR_JWT = "";
    public static String PULSAR_TOPIC = "persistent://public/default/sample-test";
    public static String PULSAR_SUBSCRIPTION_NAME = "sample-test";

    public static void main(String[] argv) {

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
                .allowTlsInsecureConnection(true)
                .build()) {
            try (Consumer<byte[]> consumer = client.newConsumer()
                    .topic("my-topic")
                    .subscriptionName(PULSAR_SUBSCRIPTION_NAME)
                    .subscribe()) {
                Message<byte[]> message = consumer.receive();
                try {
                    System.out.printf("Message Received with Data : %s", new String(message.getData()));
                    consumer.acknowledge(message);
                } catch (Exception e ) {
                    consumer.negativeAcknowledge(message);
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
