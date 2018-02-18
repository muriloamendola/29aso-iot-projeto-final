const mqtt = require('mqtt');
const client  = mqtt.connect({ host: 'iot.eclipse.org', port: 1883 });
 
client.on('connect', () => {
  console.log('Conectado!')
  client.publish('29asoIoTMIL', 'ON')
})