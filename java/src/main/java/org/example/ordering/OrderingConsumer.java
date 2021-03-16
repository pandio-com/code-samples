package org.example.ordering;

import org.apache.pulsar.client.api.*;
import java.util.concurrent.TimeUnit;

public class OrderingConsumer {
    public static String PULSAR_URL = "{{ pulsar_url }}";
    public static String PULSAR_JWT = "{{ pulsar_jwt }}";
    public static String PULSAR_TOPIC = "persistent://{{pulsar_tenant}}/{{pulsar_namespace}}/java-simple";
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
                    .subscriptionType(SubscriptionType.Exclusive) // Exclusive type is required for ordering
                    .subscriptionInitialPosition(SubscriptionInitialPosition.Latest)
                    .receiverQueueSize(1000)
                    .subscribe()) {
                Boolean loop = true;
                int key = 0;
                int index = 0;
                int newKey = 0;
                int print = 0;
                while (loop) {
                    Message<byte[]> message = consumer.receive(5000, TimeUnit.MILLISECONDS);
                    if (message != null) {
                        try {
                            newKey = Integer.parseInt(new String(message.getData()), 10);
                            if (newKey - key != 1) {
                                System.out.printf("\nSequence is broken. Old key: %d, new key: %s \n", key, newKey);
                            }
                            key = newKey;
                            consumer.acknowledge(message);
                        } catch (Exception e ) {
                            System.out.println(e);
                            consumer.negativeAcknowledge(message);
                            e.printStackTrace();
                        }
                        if (index % 500 == 0) {
                            System.out.println(index);
                        }
                        index++;
                    } else {
                        loop = false;
                    } 
                }
                System.out.printf("\n Last value is: index=%d, key=%d, newKey=%s", index, key, newKey);
                consumer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
