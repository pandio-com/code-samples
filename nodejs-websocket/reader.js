const WebSocket = require('ws');
const path = require('path');

const pulsarURL = "{{ pulsar_url }}"
const pulsarJWT = "{{ pulsar_jwt }}"
// It must be non-partition topic or partition of partitioned topic
const pulsarTopic = "persistent/public/default/topic-go-pandio-go-partition-0/" 

let topic = path.join(pulsarURL, '/v2/reader/', pulsarTopic);
let ws = new WebSocket(topic, {headers :{Authorization: "Bearer  "+ pulsarJWT}});

ws.on('message', function(message) {
    var receiveMsg = JSON.parse(message);
    console.log('Received: %s - payload: %s', message, new Buffer.from(receiveMsg.payload, 'base64').toString());
    var ackMsg = {"messageId" : receiveMsg.messageId};
    ws.send(JSON.stringify(ackMsg));
});

ws.on("error", function(err) {
    console.log('received error: %s', err);
});
