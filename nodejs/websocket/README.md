# Websocket Producer and Consumer

Websocket Pulsar producer and consumer in node.

```
npm install
```
first run above command for install dependency



Update `consumer.js` and `producer.js` and add your `pulsarURL` and `pulsarJWT` as provided in the Pandio UI on your cluster details page.

Then, start one or more consumers:

```
npm run consumer
```

You should see output similar to this:
```
> node consumer.js

```

Then, in a separate terminal, start producing messages:
```
npm run producer 
```

You should see output similar to this:
```
> node producer.js

received ack: {"result":"ok","messageId":"CJ4CEAAYAA=="}
```

Back in the consumer terminal window, you should see your messages being logged.
```
Received: {"messageId":"CJ4CEAAYAA==","payload":"SGVsbG8gV29ybGQh","properties":{},"publishTime":"2020-11-18T09:58:23.355Z"} - payload: Hello World!
```


