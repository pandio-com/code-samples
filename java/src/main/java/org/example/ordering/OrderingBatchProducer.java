package org.example.ordering;

import org.apache.pulsar.client.api.*;


public class OrderingBatchProducer {
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
                .enableBatching(true)
                .topic(PULSAR_TOPIC)
                .create();

        MessageId[] msgArray;
        for (int index = 0; index < 200000; index++) {
            String body = String.valueOf(index);
            byte[] byteBody = body.getBytes();
            producer.newMessage()
                .key(String.valueOf(index))
                .value(byteBody)
                .sendAsync();
            if (index % 1000 == 0) {
                System.out.println(index);
                try {
                    Thread.sleep(1000);    
                } catch (Exception e ) {
                    e.printStackTrace();
                }
            }
        }
        producer.close();
        client.close();
    }
}
