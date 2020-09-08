package org.example;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.ExecutionException;

public class SimpleConsumer {
    public static String PULSAR_URL = "pulsar+ssl://pulsar.staging.pandio.com:6651";
    public static String PULSAR_JWT = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYW5kaW8tLWRldi1wdWxzYXItYWRtaW4ifQ.cVcxGbYbEUqQNHhnl-6FUEqSMusg7oY1fySGLSvZHf3CXu_mwEHCbu7C3HOsYLTiqW7GkEQk4U04waO6I8GEpKVU59FSY91enltmMLY1WBn6TfaBdzi2X-A4n4WHpAilM0Y8sjjlpXULexkvrj7YPNentgfmwOAFy8CdCASS38LWNj6jKeg38Vb638O3Knkg4HSWFaLi5DqgaBB_Y7D5Sa_J6q6P_rMnoAdd2SvH1gRz6Z5we763H9TkiwJN3K1gYAkcEPtFLyPomWuzi1gRZUFuWqpg08-oHWpNi_-4eYyo6krNz2gCpGMeKW167w0_gTxhsmwdQIO2vkojmxEt4Q";
    public static String PULSAR_TOPIC = "persistent://public/default/sample-test";
    public static String PULSAR_SUBSCRIPTION_NAME = "sample-test";

    public static void main(String[] argv) {

        try (PulsarClient client = PulsarClient.builder()
                .serviceUrl(PULSAR_URL)
                .authentication(AuthenticationFactory.token(PULSAR_JWT))
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
