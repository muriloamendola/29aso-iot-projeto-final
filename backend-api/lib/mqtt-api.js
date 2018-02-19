const mqtt = require('mqtt');
const mqttOptions = { host: 'iot.eclipse.org', port: 1883 };
const TOPIC = '29asoIoTMIL';

const publish = message => {
  const client  = mqtt.connect(mqttOptions);
  client.on('connect', () => {
    client.publish(TOPIC, message, {}, () => {
      client.end();
    });   
  });  
}

module.exports = { publish }; 