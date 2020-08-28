# Simple Producer and Consumer

Simple Pulsar producer and consumer in Python.

Update `consumer.py` and `producer.py` and add your `pulsarURL` and `pulsarJWT` as provided in the Pandio UI on your cluster details page.

Install pulsar-client:
```
pip3 install pulsar-client
```

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

```

Back in the consumer terminal window, you should see your messages being logged.
```

```


