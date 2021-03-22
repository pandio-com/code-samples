# Publisher Load Testing

This contains a CLI utility that creates lots of parallel publishers to a Pulsar topic through pulsar go client running in goroutines. It's not productionized and is mostly meant for load testing. Think of this as `pulsar-perf producer` scalable through containers.

## Building Container Image

```
docker build -t max-publishers .
docker tag max-publishers gcr.io/pandio-281116/test-max-publishers:v3
docker push gcr.io/pandio-281116/test-max-publishers:v3
```

## Configuration

The publisher can be configured using the following environment variables.

| Env Var | Description | Default |
| --- | --- | --- |
| `PULSAR_URL` | URL of the Pulsar Instance. e.g. `pulsar+ssl://pandio--test.us-west-2.aws.pulsar.pandio.com:6651` | "" |
| `PULSAR_JWT` | JWT to authenticate Pulsar instance with if required. | "" |
| `PULSAR_TOPIC` | Pulsar topic where messages should be published. e.g. `non-persistent://public/default/load-test` | "" |
| `NUMBER_OF_GOROUTINE` | Number of publishers to create on the above topic. e.g. `1000` | "" |
| `TIME_DURATION_FOR_PUBLISH_MSG` | Interval over which messages are being published. e.g. `60` | "" |
| `NUMBER_OF_MSG_OVER_DURATION` | How many messages to publish over the set duration. e.g. `60` | "" |
| `MSG_LENGTH` | Message length in characters. e.g. 1000 | "" |
| `PULSAR_ALLOW_INSECURE_TLS` | Disable TLS certificate and host verification for Pulsar | `false` |
| `PULSAR_CONNECTION_TIMEOUT` | Pulsar connection timeout | `30s` |
| `PULSAR_OPERATION_TIMEOUT` | Pulsar operation timeout | `30s` |
| `TOPIC_PREFIX` | Prefix of the topics when publishing to multiple topics | "" |
| `NUMBER_OF_GOROUTINE_FOR_SUBSCRIBER` | Number of subscribers | "" |
| `PRODUCER_PREFIX_NAME` | Prefix to producer name when running multiple publishers | "" |
| `PULSAR_URL_FOR_HTTP` | Pulsar Admin URL. Needed when creating topics. | "" |
| `PULSAR_NAMESPACE` | Namespace in which multiple topics are to be created. Needed when creating topics. | "" |
| `NUMBER_OF_TOPIC` | Number of topics to create in the specified namespace. | "" |
