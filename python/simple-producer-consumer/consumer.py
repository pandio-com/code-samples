import pulsar

pulsarURL = '{{ pulsar_url }}'
pulsarJWT = '{{ pulsar_jwt }}'
pulsarTopic = 'persistent://public/default/python-simple'
subscriptionName = 'sample'

client = pulsar.Client(pulsarURL, authentication=pulsar.AuthenticationToken(pulsarJWT))

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
