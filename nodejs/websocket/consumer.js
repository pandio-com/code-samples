const path = require('path');
const WebSocket = require('ws');

const pulsarURL = "{{ pulsar_url }}"
const pulsarJWT = "{{ pulsar_jwt }}"
const pulsarTopic = "persistent/public/default/nodejs-websocket"
const subscriptionName = "sample"

const topic = path.join(pulsarURL, '/v2/consumer/', pulsarTopic, subscriptionName);;
const ws = new WebSocket(topic, {
  headers : {
    Authorization: "Bearer  " + pulsarJWT
  }
});

ws.on('message', function (message) {
  const receiveMsg = JSON.parse(message);
  console.log('Received: %s - payload: %s', message, new Buffer.from(receiveMsg.payload, 'base64').toString());
  const ackMsg = {
    "messageId": receiveMsg.messageId
  };
  ws.send(JSON.stringify(ackMsg));
});

ws.on("error", function(message) {
  console.log('received error: %s', message);
});
