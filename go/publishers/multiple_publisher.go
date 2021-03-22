package main

import (
	"context"
	"fmt"
	"github.com/apache/pulsar-client-go/pulsar"
	"github.com/rs/xid"
	"log"
	"math/rand"
	"os"
	"os/signal"
	"strconv"
	"time"
)

func withMultipleTopic(cfg AppConfig) {

	var ctx context.Context
	var cancel context.CancelFunc
	{
		ctx, cancel = context.WithCancel(context.Background())
		sig := make(chan os.Signal, 1)
		signal.Notify(sig, os.Interrupt)
		go func() {
			s := <-sig
			fmt.Println("signal", s.String())
			cancel()
		}()
	}

	clientOptions := pulsar.ClientOptions{
		URL:                        cfg.PulsarURL,
		ConnectionTimeout:          cfg.ConnectionTimeout,
		OperationTimeout:           cfg.OperationTimeout,
		TLSAllowInsecureConnection: cfg.PulsarAllowTLSInsecure,
	}

	if v := cfg.PulsarJWL; v != "" {
		clientOptions.Authentication = pulsar.NewAuthenticationToken(v)
	}

	client, err := pulsar.NewClient(clientOptions)
	if err != nil {
		panic(err)
	}

	for i := 0; i < cfg.NumberOfGoroutine; i++ {
		i := i
		go func() {
			topic := cfg.PulsarTopic + cfg.TopicPrefix + "-" + strconv.Itoa(i)
			producer, err := client.CreateProducer(pulsar.ProducerOptions{
				Topic: topic,
			})
			if err != nil 	{
				panic(err)
			}

			go func() {
				consumer, err := client.Subscribe(pulsar.ConsumerOptions{
					Topic:            topic,
					SubscriptionName: xid.New().String(),
					Type:             pulsar.Shared,
				})
				defer consumer.Close()
				if err != nil {
					log.Fatalf("could not create pulsar consumer: %v", err)
				}
				for {
					select {
					case <-ctx.Done():
						return
					case msg := <-consumer.Chan():
						fmt.Println("Message consumed by subscriber", msg)
					}
				}
			}()

			duration := rand.Intn(cfg.TimeDurationForPublishMsg)
			randomDuration := duration
			for {
				time.Sleep(time.Duration(randomDuration) * time.Second)
				randomDuration = randomDuration + duration
				for numOfMsg := 0; numOfMsg < cfg.NumberOfMsgOverDuration; numOfMsg++ {
					msg := StringWithCharset(rand.Intn(cfg.MsgLength), []byte(charset))
					mID, err := producer.Send(context.Background(), &pulsar.ProducerMessage{Payload: []byte(msg)})
					if err != nil {
						panic(err)
					}
					fmt.Println(fmt.Sprintf("Message published by %d", i), mID)
				}
			}



		}()
	}

	<-ctx.Done()
}
