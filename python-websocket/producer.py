import websocket
import base64
import json

pulsarURL = '{{ pulsar_url }}'
pulsarJWT = '{{ pulsar_jwt }}'
pulsarTopic = 'persistent/public/default/topic-go-pandio-go/'

ws = websocket.create_connection(pulsarURL + pulsarTopic, header=["Authorization: Bearer "+pulsarJWT])
# Send one message as JSON
ws.send(json.dumps({
    'payload': base64.b64encode(b'Hello world!').decode('utf-8'),
}))

response = json.loads(ws.recv())
if response['result'] == 'ok':
    print("received ack: {}".format(response))
else:
    print('Failed to publish message:', response)
ws.close()
