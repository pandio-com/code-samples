package main

import (
	"context"
	"fmt"
	"log"

	"github.com/apache/pulsar-client-go/pulsar"
)

func main() {

	const (
		pulsarURL        string = "pulsar+ssl://pulsar-test.pulsar.pandio.app.staging.pandio.com:6651"
		pulsarJWT        string = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYW5kaW8tcHVsc2FyLXRlc3QtYWRtaW4ifQ.RmfL9S-fJzKbRyYbnN5vJost5vHO27cWQZILH1-GJOpuQyeAZ8zffgf2nxT-fdMnHB4ShNi0C1TCrQ9ogjARzFVF8LiOHo4QdH4vWaOr5mCqkOcIDggR0q8v-yoBpkX2dc_oaiInq0kOHxe3NYDvkz5ryUZ-bIZncSK2wClBj_H9Cj3k5sg04QG28jcbBfqT77smXRsxUuZ1F2xryEhJGEgJHghKkIHdofs6nNANUqleeuAkhfoLhNLMFwgQ3zmgDjbGT58gCkPkvV7l2EIB1CdhTFjfyZMENRXJOZK1US3N9GIZw4VCgdjj1-SxMSMVtislH9L0ad20qI6ebY8zNA"
		pulsarTopic      string = "producer-consumer-test"
		subscriptionName string = "sub-pct"
	)

	log.Println(fmt.Sprintf("Starting app. Consuming from topic '%s' on cluster '%s'...", pulsarTopic, pulsarURL))

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
