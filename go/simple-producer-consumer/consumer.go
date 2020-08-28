package main

import (
	"context"
	"log"

	"github.com/apache/pulsar-client-go/pulsar"
)

func main() {

	const (
		pulsarURL        string = "{{ pulsar_url }}"
		pulsarJWT        string = "{{ pulsar_jwt }}"
		pulsarTopic      string = "producer-consumer-test"
		subscriptionName string = "sub-pct"
	)

	// create pulsar client
	client, err := pulsar.NewClient(pulsar.ClientOptions{
		URL:            pulsarURL,
		Authentication: pulsar.NewAuthenticationToken(pulsarJWT),
	})
	if err != nil {
		log.Fatalf("could not create pulsar client: %v", err)
	}
	defer client.Close()

	// create consumer
	consumer, err := client.Subscribe(pulsar.ConsumerOptions{
		Topic:            pulsarTopic,
		SubscriptionName: subscriptionName,
		Type:             pulsar.Shared,
	})
	if err != nil {
		log.Fatalf("could not create pulsar consumer: %v", err)
	}
	defer consumer.Close()

	// consume messages
	for i := 0; i < 10; i++ {
		msg, err := consumer.Receive(context.Background())
		if err != nil {
			log.Fatalf("could not consume message: %v", err)
		}

		log.Printf("received message ID: %#v -- content: '%s'", msg.ID(), string(msg.Payload()))

		// acknowldege message
		consumer.Ack(msg)
	}

	// unsubscribe
	if err := consumer.Unsubscribe(); err != nil {
		log.Fatalf("could not unsubscribe: %v", err)
	}
}
