const path = require('path');
const WebSocket = require('ws');

const pulsarURL = "{{ pulsar_url }}"
const pulsarJWT = "{{ pulsar_jwt }}"
const pulsarTopic = "persistent/{{pulsar_tenant}}/{{pulsar_namespace}}/nodejs-websocket"

const topic = path.join(pulsarURL, '/v2/producer/', pulsarTopic);
const ws = new WebSocket(topic, { headers: { Authorization: "Bearer  " + pulsarJWT } });

var message = {
	"payload": new Buffer.from("Hello World!").toString('base64')
};

ws.on('open', function () {
	// Send one message
	ws.send(JSON.stringify(message));
});

ws.on('message', function (message) {
	console.log('received ack: %s', message);
	ws.close()
});

ws.on("error", function (message) {
	console.log('received error: %s', message);
});
