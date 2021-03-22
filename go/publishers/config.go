package main

import (
	"github.com/joho/godotenv"
	"github.com/kelseyhightower/envconfig"
	"time"
)

var AllConfig AppConfig

type AppConfig struct {
	PulsarURL                      string        `envconfig:"PULSAR_URL"`
	PulsarJWL                      string        `envconfig:"PULSAR_JWT"`
	PulsarTopic                    string        `envconfig:"PULSAR_TOPIC"`
	NumberOfGoroutine              int           `envconfig:"NUMBER_OF_GOROUTINE"`
	NumberOfMsgOverDuration        int           `envconfig:"NUMBER_OF_MSG_OVER_DURATION"`
	TimeDurationForPublishMsg      int           `envconfig:"TIME_DURATION_FOR_PUBLISH_MSG"`
	PulsarAllowTLSInsecure         bool          `envconfig:"PULSAR_ALLOW_INSECURE_TLS"`
	ConnectionTimeout              time.Duration `envconfig:"PULSAR_CONNECTION_TIMEOUT"`
	OperationTimeout               time.Duration `envconfig:"PULSAR_OPERATION_TIMEOUT"`
	MsgLength                      int           `envconfig:"MSG_LENGTH"`
	TopicPrefix                    string        `envconfig:"TOPIC_PREFIX"`
	NumberOfGoroutineForSubscriber int           `envconfig:"NUMBER_OF_GOROUTINE_FOR_SUBSCRIBER"`
	PulsarNamespace                string        `envconfig:"PULSAR_NAMESPACE"`
	NumberOfTopic                  int           `envconfig:"NUMBER_OF_TOPIC"`
	PulsarURLForHTTP               string        `envconfig:"PULSAR_URL_FOR_HTTP"`
	ProducerPrefixName             string        `envconfig:"PRODUCER_PREFIX_NAME"`
}

func CollectConfig() AppConfig {
	_ = godotenv.Load(".env")

	AllConfig = AppConfig{}

	err := envconfig.Process("", &AllConfig)
	if err != nil {
		panic(err)
	}
	return AllConfig
}
