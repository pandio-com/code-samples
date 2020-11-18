# Websocket Producer and Consumer

Websocket Pulsar producer and consumer in python.

Update `consumer.py` and `producer.py` and add your `pulsarURL` and `pulsarJWT` as provided in the Pandio UI on your cluster details page.

Then, start one or more consumers:

```
python3 consumer.py
```

You should see output similar to this:
```

```

Then, in a separate terminal, start producing messages:
```
python3 producer.py
```

You should see output similar to this:
```
received ack: {'result': 'ok', 'messageId': 'CKICEAEYAA=='}
```

Back in the consumer terminal window, you should see your messages being logged.
```
Received: {'messageId': 'CKICEAEYAA==', 'payload': 'SGVsbG8gd29ybGQh', 'properties': {}, 'publishTime': '2020-11-18T10:34:58.117Z'} - payload: Hello world!
```