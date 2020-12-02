const pulsarURL = "{{ pulsar_url }}"
const pulsarJWT = "{{ pulsar_jwt }}"
const pulsarTopic = "persistent/public/default/topic-go-pandio-go/"
const subscriptionName = "my-subscription-name"

let WebSocket = require('ws'),
    topic = pulsarURL + pulsarTopic + subscriptionName,
    ws = new WebSocket(topic, {headers :{Authorization: "Bearer  "+ pulsarJWT}});

ws.on('message', function(message) {
    var receiveMsg = JSON.parse(message);
    console.log('Received: %s - payload: %s', message, new Buffer.from(receiveMsg.payload, 'base64').toString());
    var ackMsg = {"messageId" : receiveMsg.messageId};
    ws.send(JSON.stringify(ackMsg));
});

ws.on("error", function(message) {
    console.log('received error: %s', message);
});
