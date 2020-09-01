import pulsar

pulsarURL = '{{ pulsar_url }}'
pulsarJWT = '{{ pulsar_jwt }}'
pulsarTopic = 'persistent://public/default/topic-go-pandio-go'

client = pulsar.Client(pulsarURL,authentication=pulsar.AuthenticationToken(pulsarJWT))

producer = client.create_producer(pulsarTopic)

producer.send('Hello world!'.encode('utf-8'))

client.close()
