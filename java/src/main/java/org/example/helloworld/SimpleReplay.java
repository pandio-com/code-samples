package org.example.helloworld;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.*;

public class SimpleReplay {
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
            try (Reader<byte[]> reader = client.newReader()
                    .topic(PULSAR_TOPIC)
                    .startMessageId(new MessageIdImpl(21, 0, 0)) // ledger id, entryid, partion index
                    .create()) {
                Boolean loop = true;
                System.out.printf("Earliest message id: %s", MessageId.earliest.toString());
                try {
                    while (loop) {
                        Message<byte[]> message = reader.readNext();
                        System.out.printf("Message Received with Data : %s\n", new String(message.getData()));
                        System.out.printf("Message Received with Data : %s\n", message.getSequenceId());
                    }
                } catch (Exception e ) {
                    e.printStackTrace();
                } finally {
                    reader.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
