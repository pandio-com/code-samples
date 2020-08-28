package main

import (
	"context"
	"flag"
	"fmt"
	"log"

	"github.com/apache/pulsar-client-go/pulsar"
)

func main() {

	const (
		pulsarURL   string = "pulsar+ssl://pulsar-test.pulsar.pandio.app.staging.pandio.com:6651"
		pulsarJWT   string = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYW5kaW8tcHVsc2FyLXRlc3QtYWRtaW4ifQ.RmfL9S-fJzKbRyYbnN5vJost5vHO27cWQZILH1-GJOpuQyeAZ8zffgf2nxT-fdMnHB4ShNi0C1TCrQ9ogjARzFVF8LiOHo4QdH4vWaOr5mCqkOcIDggR0q8v-yoBpkX2dc_oaiInq0kOHxe3NYDvkz5ryUZ-bIZncSK2wClBj_H9Cj3k5sg04QG28jcbBfqT77smXRsxUuZ1F2xryEhJGEgJHghKkIHdofs6nNANUqleeuAkhfoLhNLMFwgQ3zmgDjbGT58gCkPkvV7l2EIB1CdhTFjfyZMENRXJOZK1US3N9GIZw4VCgdjj1-SxMSMVtislH9L0ad20qI6ebY8zNA"
		pulsarTopic string = "producer-consumer-test"
	)

	message := flag.String("message", "hello world", "the message you want to produce")
	flag.Parse()

	log.Println(fmt.Sprintf("Starting app. Sending '%s' to topic '%s' on cluster '%s'...", *message, pulsarTopic, pulsarURL))

	// create pulsar client
	client, err := pulsar.NewClient(pulsar.ClientOptions{
		URL:            pulsarURL,
		Authentication: pulsar.NewAuthenticationToken(pulsarJWT),
	})
	if err != nil {
		log.Fatal(fmt.Sprintf("could not create pulsar client: %v", err))
	}
	defer client.Close()

	// create producer
	producer, err := client.CreateProducer(pulsar.ProducerOptions{
		Topic: pulsarTopic,
	})
	if err != nil {
		log.Fatal(fmt.Sprintf("could not create pulsar producer: %v", err))
	}
	defer producer.Close()

	// produce the message
	ctx := context.Background()
	msgId, err := producer.Send(ctx, &pulsar.ProducerMessage{
		Payload: []byte(*message),
	})

	if err != nil {
		log.Fatal(err)
	} else {
		log.Println("Published message: ", msgId)
	}

}
