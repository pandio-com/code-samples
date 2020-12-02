# Simple Producer and Consumer

Simple Pulsar producer and consumer in Python.

Update `consumer.py` and `producer.py` and add your `pulsarURL` and `pulsarJWT` as provided in the Pandio UI on your cluster details page.

Install pulsar-client:

Note: Python needs a customized pulsar client at the moment because the official client does not have TLS SNI support, which is required for connecting to Pulsar instance(s) managed by Pandio.

```
python3 -m venv .venv
source .venv/bin/activate
pip3 install --extra-index-url https://cdn.pandio.com/pypi pandio-pulsar-client
pip3 install pandio-pulsar-client
```

Then, start one or more consumers:
```
python3 consumer.py
```

You should see output similar to this:
```
2020-12-02 17:47:38.910 INFO  [140616004298560] Client:88 | Subscribing on Topic :persistent://public/default/python-simple
2020-12-02 17:47:38.917 INFO  [140616004298560] ConnectionPool:85 | Created connection for pulsar+ssl://pulsar.staging.pandio.com:6651
2020-12-02 17:47:39.190 INFO  [140615982896704] ClientConnection:343 | [192.168.9.9:54110 -> 35.225.185.117:6651] Connected to broker
2020-12-02 17:47:40.309 INFO  [140615982896704] AckGroupingTrackerEnabled:53 | ACK grouping is enabled, grouping time 100ms, grouping max size 1000
2020-12-02 17:47:40.309 INFO  [140615982896704] AckGroupingTrackerEnabled:53 | ACK grouping is enabled, grouping time 100ms, grouping max size 1000
2020-12-02 17:47:40.309 INFO  [140615982896704] HandlerBase:53 | [persistent://public/default/python-simple-partition-0, sample, 0] Getting connection from pool
2020-12-02 17:47:40.309 INFO  [140615982896704] HandlerBase:53 | [persistent://public/default/python-simple-partition-1, sample, 1] Getting connection from pool
2020-12-02 17:47:40.631 INFO  [140615982896704] ConnectionPool:85 | Created connection for pulsar://pulsar-broker-0.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:40.649 INFO  [140615982896704] ConnectionPool:85 | Created connection for pulsar://pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:40.927 INFO  [140615982896704] ClientConnection:345 | [192.168.9.9:54128 -> 35.225.185.117:6651] Connected to broker through proxy. Logical broker: pulsar://pulsar-broker-0.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:40.937 INFO  [140615982896704] ClientConnection:345 | [192.168.9.9:54130 -> 35.225.185.117:6651] Connected to broker through proxy. Logical broker: pulsar://pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:42.102 INFO  [140615982896704] ConsumerImpl:199 | [persistent://public/default/python-simple-partition-0, sample, 0] Created consumer on broker [192.168.9.9:54128 -> 35.225.185.117:6651]
2020-12-02 17:47:42.106 INFO  [140615982896704] ConsumerImpl:199 | [persistent://public/default/python-simple-partition-1, sample, 1] Created consumer on broker [192.168.9.9:54130 -> 35.225.185.117:6651]
2020-12-02 17:47:42.106 INFO  [140615982896704] PartitionedConsumerImpl:302 | Successfully Subscribed to Partitioned Topic - persistent://public/default/python-simple with - 2 Partitions.
```

Then, in a separate terminal, start producing messages:
```
python3 producer.py
```

You should see output similar to this:
```
2020-12-02 17:47:48.480 INFO  [140376377325376] ConnectionPool:85 | Created connection for pulsar+ssl://pulsar.staging.pandio.com:6651
2020-12-02 17:47:48.753 INFO  [140376355661376] ClientConnection:343 | [192.168.9.9:54196 -> 35.225.185.117:6651] Connected to broker
2020-12-02 17:47:49.843 INFO  [140376355661376] HandlerBase:53 | [persistent://public/default/python-simple-partition-0, ] Getting connection from pool
2020-12-02 17:47:49.843 INFO  [140376355661376] HandlerBase:53 | [persistent://public/default/python-simple-partition-1, ] Getting connection from pool
2020-12-02 17:47:50.126 INFO  [140376355661376] ConnectionPool:85 | Created connection for pulsar://pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:50.132 INFO  [140376355661376] ConnectionPool:85 | Created connection for pulsar://pulsar-broker-0.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:50.393 INFO  [140376355661376] ClientConnection:345 | [192.168.9.9:54208 -> 35.225.185.117:6651] Connected to broker through proxy. Logical broker: pulsar://pulsar-broker-0.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:50.406 INFO  [140376355661376] ClientConnection:345 | [192.168.9.9:54206 -> 35.225.185.117:6651] Connected to broker through proxy. Logical broker: pulsar://pulsar-broker-1.pulsar-broker.pandio--dev-pulsar.svc.cluster.local:6650
2020-12-02 17:47:51.437 INFO  [140376355661376] ProducerImpl:165 | [persistent://public/default/python-simple-partition-0, ] Created producer on broker [192.168.9.9:54208 -> 35.225.185.117:6651]
2020-12-02 17:47:51.513 INFO  [140376355661376] ProducerImpl:165 | [persistent://public/default/python-simple-partition-1, ] Created producer on broker [192.168.9.9:54206 -> 35.225.185.117:6651]
2020-12-02 17:47:51.778 INFO  [140376377325376] ClientImpl:481 | Closing Pulsar client
2020-12-02 17:47:51.778 INFO  [140376377325376] ProducerImpl:502 | [persistent://public/default/python-simple-partition-0, pandio--dev-pulsar-64-89374] Closing producer for topic persistent://public/default/python-simple-partition-0
2020-12-02 17:47:51.778 INFO  [140376377325376] ProducerImpl:502 | [persistent://public/default/python-simple-partition-1, pandio--dev-pulsar-63-84642] Closing producer for topic persistent://public/default/python-simple-partition-1
2020-12-02 17:47:52.038 INFO  [140376355661376] ProducerImpl:545 | [persistent://public/default/python-simple-partition-0, pandio--dev-pulsar-64-89374] Closed producer
2020-12-02 17:47:52.053 INFO  [140376355661376] ProducerImpl:545 | [persistent://public/default/python-simple-partition-1, pandio--dev-pulsar-63-84642] Closed producer
2020-12-02 17:47:52.053 INFO  [140376355661376] ClientConnection:1380 | [192.168.9.9:54196 -> 35.225.185.117:6651] Connection closed
2020-12-02 17:47:52.053 INFO  [140376355661376] ClientConnection:1380 | [192.168.9.9:54208 -> 35.225.185.117:6651] Connection closed
2020-12-02 17:47:52.053 INFO  [140376355661376] ClientConnection:1380 | [192.168.9.9:54206 -> 35.225.185.117:6651] Connection closed
```

Back in the consumer terminal window, you should see your messages being logged.
```
Received message 'b'Hello world!'' id='(92035,0,0,-1)'
```

Leaving the virtualenv

```
deactivate
```

