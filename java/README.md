# Simple Producer and Consumer

Simple Pulsar producer and consumer in Java.

Update `SimpleConsumer.java` and `SimpleProducer.java` and add your `PULSAR_URL` and `PULSAR_JWT` as provided in the Pandio UI on your cluster details page.

Then, start one or more consumers:

```
./gradlew runSimpleConsumer
```

You should see output similar to this:
```
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0xeaee507a, L:/192.168.9.9:54264 - R:pulsar-test.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerStatsRecorderImpl - Starting Pulsar consumer status recorder with config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerStatsRecorderImpl - Pulsar client config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerStatsRecorderImpl - Starting Pulsar consumer status recorder with config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerStatsRecorderImpl - Pulsar client config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0xa268f8c7, L:/192.168.9.9:54282 - R:pulsar-test.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0xa268f8c7, L:/192.168.9.9:54282 - R:pulsar-test.pandio.com/35.225.185.117:6651] Connected through proxy to target broker at pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0x455176aa, L:/192.168.9.9:54284 - R:pulsar-test.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0x455176aa, L:/192.168.9.9:54284 - R:pulsar-test.pandio.com/35.225.185.117:6651] Connected through proxy to target broker at pulsar-broker-2.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-1][sample] Subscribing to topic on cnx [id: 0x455176aa, L:/192.168.9.9:54284 - R:pulsar-test.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-0][sample] Subscribing to topic on cnx [id: 0xa268f8c7, L:/192.168.9.9:54282 - R:pulsar-test.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-1][sample] Subscribed to topic on pulsar-test.pandio.com/35.225.185.117:6651 -- consumer: 1
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-0][sample] Subscribed to topic on pulsar-test.pandio.com/35.225.185.117:6651 -- consumer: 0
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.MultiTopicsConsumerImpl - [persistent://public/default/java-simple] [sample] Success subscribe new topic persistent://public/default/java-simple in topics consumer, partitions: 2, allTopicPartitionsNumber: 2
```

Then, in a separate terminal, start producing messages:
```
./gradlew runSimpleProducer
```

You should see output similar to this:
```
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0x8e9ae540, L:/192.168.9.9:55624 - R:pulsar-test.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerStatsRecorderImpl - Starting Pulsar producer perf with config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerStatsRecorderImpl - Pulsar client config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerStatsRecorderImpl - Starting Pulsar producer perf with config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerStatsRecorderImpl - Pulsar client config: {
...
}
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0xdb419aa1, L:/192.168.9.9:58320 - R:pulsar.staging.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0xdb419aa1, L:/192.168.9.9:58320 - R:pulsar.staging.pandio.com/35.225.185.117:6651] Connected through proxy to target broker at pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConnectionPool - [[id: 0x52ee28d2, L:/192.168.9.9:58322 - R:pulsar.staging.pandio.com/35.225.185.117:6651]] Connected to server
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0x52ee28d2, L:/192.168.9.9:58322 - R:pulsar.staging.pandio.com/35.225.185.117:6651] Connected through proxy to target broker at pulsar-broker-2.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-0] [null] Creating producer on cnx [id: 0xdb419aa1, L:/192.168.9.9:58320 - R:pulsar.staging.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-1] [null] Creating producer on cnx [id: 0x52ee28d2, L:/192.168.9.9:58322 - R:pulsar.staging.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-0] [pandio--dev-pulsar-63-85415] Created producer on cnx [id: 0xdb419aa1, L:/192.168.9.9:58320 - R:pulsar.staging.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-1] [pandio--dev-pulsar-62-44530] Created producer on cnx [id: 0x52ee28d2, L:/192.168.9.9:58322 - R:pulsar.staging.pandio.com/35.225.185.117:6651]
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.PartitionedProducerImpl - [persistent://public/default/java-simple] Created partitioned producer
[pulsar-timer-4-1] INFO com.scurrilous.circe.checksum.Crc32cIntChecksum - SSE4.2 CRC32C provider initialized
Message Published: 94121:0:0:0
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-0] [pandio--dev-pulsar-63-85415] Closed Producer
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ProducerImpl - [persistent://public/default/java-simple-partition-1] [pandio--dev-pulsar-62-44530] Closed Producer
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.PartitionedProducerImpl - [persistent://public/default/java-simple] Closed Partitioned Producer
[main] INFO org.apache.pulsar.client.impl.PulsarClientImpl - Client closing. URL: pulsar+ssl://pulsar.staging.pandio.com:6651
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0x4a77e27c, L:/192.168.9.9:58292 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0xdb419aa1, L:/192.168.9.9:58320 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0x52ee28d2, L:/192.168.9.9:58322 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
```

Back in the consumer terminal window, you should see your messages being logged.
```
Message Received with Data : Test Message
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-0] [sample] Closed consumer
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ConsumerImpl - [persistent://public/default/java-simple-partition-1] [sample] Closed consumer
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.MultiTopicsConsumerImpl - [persistent://public/default/java-simple] [sample] Closed Topics Consumer
[main] INFO org.apache.pulsar.client.impl.PulsarClientImpl - Client closing. URL: pulsar+ssl://pulsar.staging.pandio.com:6651
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0x8009c004, L:/192.168.9.9:57832 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0xf7ea1b32, L:/192.168.9.9:57858 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
[pulsar-client-io-1-1] INFO org.apache.pulsar.client.impl.ClientCnx - [id: 0xb667cab7, L:/192.168.9.9:57860 ! R:pulsar.staging.pandio.com/35.225.185.117:6651] Disconnected
```

## Produce & Consume with Schema

If you want type safety for your messages, then you can define a `schema` while producing and consumeing messages. Here we used [generic AVRO schema](https://pulsar.apache.org/docs/en/schema-understand/#generic).

Update `SimpleConsumerWithSchema.java` and `SimpleProducerWithSchema.java` and add your `PULSAR_URL` and `PULSAR_JWT` as provided in the Pandio UI on your cluster details page.

Then, start one or more consumers:

```
./gradlew runSimpleConsumerWithSchema
```

Then, in a separate terminal, start producing messages:
```
./gradlew runSimpleProducerWithSchema
```