package main

import (
	"fmt"
	"github.com/go-resty/resty/v2"
	"math/rand"
	"sync"
	"time"
)

var vehiclesVINCharacterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
var sensors = []string{
	"oxygen",
	"fuel-temp",
}

func createMultipleTopic() {
	cfg := CollectConfig()
	rand.Seed(time.Now().UnixNano())
	var wg sync.WaitGroup
	for i := 0; i < cfg.NumberOfTopic; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			vehicleVIN := StringWithCharset(17, []byte(vehiclesVINCharacterSet))
			sensor := sensors[rand.Intn(2)]
			topicName := vehicleVIN + "-" + sensor
			_, err := resty.New().R().
				SetAuthToken(cfg.PulsarJWL).
				Put(cfg.PulsarURLForHTTP + "/admin/v2/persistent/" + cfg.PulsarNamespace + "/" + topicName)
			if err != nil {
				fmt.Println(err)
			}
		}()
	}
	wg.Wait()
}
