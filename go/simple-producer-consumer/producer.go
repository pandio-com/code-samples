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
		pulsarURL   string = "pulsar+ssl://<<pandio pulsar url>>"
		pulsarJWT   string = "<< pandio pulsar JWT>>"
		pulsarTopic string = "producer-consumer-test"
	)

	message := flag.String("message", "hello world", "the message you want to produce")
	flag.Parse()

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
