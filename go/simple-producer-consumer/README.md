# Simple Producer and Consumer

Simple Pulsar producer and consumer in go.

First, start the consumer:

```
go run consumer.go
```

You should see output similar to this:
```
<<content>>
```

Then, in a separate terminal, start producing messages:

```
go run producer.go -message "this is a test"
go run producer.go -message "this is another test"
```

Back in the consumer terminal window, you should see your messages being logged.
```
<<content>>
```


