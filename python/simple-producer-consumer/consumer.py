import pulsar

pulsarURL = 'pulsar+ssl://pandio--pulsar-test.us-central1.gcp.app.staging.pandio.com:6651'
pulsarJWT = 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJwYW5kaW8tLXB1bHNhci10ZXN0LWFkbWluIn0.FF2XzV8W-EvZun6b_MCAr_7NKHJ84YPepRkzz3QPfxrfqmaugBTVtrclqF3usL1PVliLPLOxdUp2aO2nuUOERh14ErhmywhjlwBrp62PhYA4E4ptnZCLIRRHjAxIuo65SX2RMqSmEiTqRAjJ4-z_5-Exe29sv5vlIK08N0Rh2NsuhhpvdyYd0iOFTqkHYPhqkXc1BQtBOZSZVOGTIzUyV5VW_VAY5QfsbWo8PeuH-CsyWli8Lg_ddSP6KbULiVAex5M78nj58mN79YJgxahTd2_Qo2PJDemuw21ieOXSRuXfyJtM_IKVdKOntMzfZ4ysEFzPixJdfg2Z6Rgqbymz1g'
pulsarTopic = 'persistent://public/default/producer-consumer-test'
subscriptionName = 'my-subscription-name'

client = pulsar.Client(pulsarURL,authentication=pulsar.AuthenticationToken(pulsarJWT))

consumer = client.subscribe(pulsarTopic,subscriptionName)

while True:
    msg = consumer.receive()
    try:
        print("Received message '{}' id='{}'".format(msg.value(), msg.message_id()))
        # Acknowledge successful processing of the message
        consumer.acknowledge(msg)
    except:
        # Message failed to be processed
        consumer.negative_acknowledge(msg)

client.close()
