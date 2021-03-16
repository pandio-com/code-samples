package org.example.ordering;

import org.apache.pulsar.client.api.*;

public class OrderingProducer {
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

        for (int index = 0; index < 4000; index++) {
            String body = String.valueOf(index);
            byte[] byteBody = body.getBytes();
            MessageId msgId = producer.newMessage()
                .key(String.valueOf(index))
                .value(byteBody)
                .send();
            System.out.println("Message Published: " + msgId);
        }


        producer.close();
        client.close();
    }
}
