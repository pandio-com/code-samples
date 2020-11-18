const pulsarURL = "{{ pulsar_url }}"
const pulsarJWT = "{{ pulsar_jwt }}"
const pulsarTopic = "persistent/public/default/topic-go-pandio-go"

let WebSocket = require('ws'),
    topic = pulsarURL + pulsarTopic,
    ws = new WebSocket(topic, {headers :{Authorization: "Bearer  "+ pulsarJWT}});

var message = {
    "payload" : new Buffer.from("Hello World!").toString('base64')
};

ws.on('open', function() {
    // Send one message
    ws.send(JSON.stringify(message));
});

ws.on('message', function(message) {
    console.log('received ack: %s', message);
    ws.close()
});

ws.on("error", function(message) {
    console.log('received error: %s', message);
});

