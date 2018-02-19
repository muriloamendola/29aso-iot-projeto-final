const mqtt = require('mqtt');
const mqttOptions = { host: 'iot.eclipse.org', port: 1883 };
const client  = mqtt.connect(mqttOptions);

const TOPIC = '29asoIoTMIL';

client.on('connect', () => {
  console.log('Conectado ao host MQTT', mqttOptions);
});

const publish = message => {
  client.publish(TOPIC, message); 
}

module.exports = { publish }; 