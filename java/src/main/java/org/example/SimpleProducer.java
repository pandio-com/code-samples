package org.example;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class SimpleProducer {
    public static String PULSAR_URL = "pulsar+ssl://pulsar.staging.pandio.com:6651";
    public static String PULSAR_JWT = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYW5kaW8tLWRldi1wdWxzYXItYWRtaW4ifQ.cVcxGbYbEUqQNHhnl-6FUEqSMusg7oY1fySGLSvZHf3CXu_mwEHCbu7C3HOsYLTiqW7GkEQk4U04waO6I8GEpKVU59FSY91enltmMLY1WBn6TfaBdzi2X-A4n4WHpAilM0Y8sjjlpXULexkvrj7YPNentgfmwOAFy8CdCASS38LWNj6jKeg38Vb638O3Knkg4HSWFaLi5DqgaBB_Y7D5Sa_J6q6P_rMnoAdd2SvH1gRz6Z5we763H9TkiwJN3K1gYAkcEPtFLyPomWuzi1gRZUFuWqpg08-oHWpNi_-4eYyo6krNz2gCpGMeKW167w0_gTxhsmwdQIO2vkojmxEt4Q";
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
