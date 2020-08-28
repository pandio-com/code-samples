# Simple Producer and Consumer

Simple Pulsar producer and consumer in go.

Update `consumer.go` and `producer.go` and add your `pulsarURL` and `pulsarJWT` as provided in the Pandio UI on your cluster details page.

Then, start one or more consumers:

```
go run consumer.go
```

You should see output similar to this:
```
INFO[0000] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] TCP connection established                    local_addr="192.168.2.4:64741" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] Connection is ready                           local_addr="192.168.2.4:64741" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] TCP connection established                    local_addr="192.168.2.4:64752" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] TCP connection established                    local_addr="192.168.2.4:64753" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connection is ready                           local_addr="192.168.2.4:64752" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connection is ready                           local_addr="192.168.2.4:64753" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connected consumer                            consumerID=2 name=fnuwh subscription=sub-pct topic=producer-consumer-test-partition-1
INFO[0001] Created consumer                              consumerID=2 name=fnuwh subscription=sub-pct topic=producer-consumer-test-partition-1
INFO[0001] Connected consumer                            consumerID=1 name=fnuwh subscription=sub-pct topic=producer-consumer-test-partition-0
INFO[0001] Created consumer                              consumerID=1 name=fnuwh subscription=sub-pct topic=producer-consumer-test-partition-0
```

Then, in a separate terminal, start producing messages:
```
go run producer.go -message "this is a test"
go run producer.go -message "this is another test"
```

You should see output similar to this:
```
INFO[0000] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] TCP connection established                    local_addr="192.168.2.4:65347" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] Connection is ready                           local_addr="192.168.2.4:65347" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0000] Connecting to broker                          remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] TCP connection established                    local_addr="192.168.2.4:65353" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] TCP connection established                    local_addr="192.168.2.4:65352" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connection is ready                           local_addr="192.168.2.4:65353" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Connection is ready                           local_addr="192.168.2.4:65352" remote_addr="pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.pandio.com:6651"
INFO[0001] Created producer                              cnx="192.168.2.4:65353 -> 35.225.185.117:6651" producerID=2 producer_name=pandio--pulsar-test-26-6 topic=producer-consumer-test-partition-1
INFO[0001] Created producer                              cnx="192.168.2.4:65352 -> 35.225.185.117:6651" producerID=1 producer_name=pandio--pulsar-test-27-4 topic=producer-consumer-test-partition-0
2020/08/28 12:50:38 Published message:  {17 2 0 0}
INFO[0001] Closing producer                              producerID=1 producer_name=pandio--pulsar-test-27-4 topic=producer-consumer-test-partition-0
INFO[0001] Closed producer                               producerID=1 producer_name=pandio--pulsar-test-27-4 topic=producer-consumer-test-partition-0
INFO[0001] Closing producer                              producerID=2 producer_name=pandio--pulsar-test-26-6 topic=producer-consumer-test-partition-1
INFO[0002] Closed producer                               producerID=2 producer_name=pandio--pulsar-test-26-6 topic=producer-consumer-test-partition-1
```

Back in the consumer terminal window, you should see your messages being logged.
```
2020/08/28 12:50:38 received message ID: pulsar.trackingMessageID{messageID:pulsar.messageID{ledgerID:17, entryID:2, batchIdx:0, partitionIdx:0}, tracker:(*pulsar.ackTracker)(nil), consumer:(*pulsar.partitionConsumer)(0xc00013a000), receivedTime:time.Time{wall:0xbfca563f9f453bd8, ext:128951388121, loc:(*time.Location)(0x4b40b40)}} -- content: 'this is a test'
2020/08/28 12:51:39 received message ID: pulsar.trackingMessageID{messageID:pulsar.messageID{ledgerID:18, entryID:0, batchIdx:0, partitionIdx:1}, tracker:(*pulsar.ackTracker)(nil), consumer:(*pulsar.partitionConsumer)(0xc00054a000), receivedTime:time.Time{wall:0xbfca565defce53b8, ext:250226091217, loc:(*time.Location)(0x4b40b40)}} -- content: 'this is another test'
```


