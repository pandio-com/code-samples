import websocket
import base64
import json

pulsarURL = '{{ pulsar_url }}'
pulsarJWT = '{{ pulsar_jwt }}'
pulsarTopic = 'persistent/public/default/topic-go-pandio-go/'
subscriptionName = 'my-subscription-name'

ws = websocket.create_connection(pulsarURL + pulsarTopic + subscriptionName, header=["Authorization: Bearer "+pulsarJWT])

while True:
    msg = json.loads(ws.recv())
    if not msg:
        break

    print("Received: {} - payload: {}".format(msg, base64.b64decode(msg['payload']).decode('utf-8')))

    ws.send(json.dumps({'messageId': msg['messageId']}))

ws.close()
