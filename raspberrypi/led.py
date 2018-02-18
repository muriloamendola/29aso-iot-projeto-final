import sys
import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO

broker = 'iot.eclipse.org'
broker_port = 1883
keep_alive_broker = 60
topic = '29asoIoTMIL'

GPIO.setmode(GPIO.BCM) 
GPIO.setup(27, GPIO.OUT)

def on_connect(client, userdata, flags, rc):
  print('Conectado ao broker: ' + str(rc))
  client.subscribe(topic)

def on_message(client, userdata, msg):
  payload = str(msg.payload.decode('utf-8'))
  print('Mensagem recebida: ' + payload)
  if (payload == '1'):
    GPIO.output(27, GPIO.HIGH)
  else:
    GPIO.output(27, GPIO.LOW)

try:
  print('Inicializando MQTT')  
  client = mqtt.Client()
  client.on_connect = on_connect
  client.on_message = on_message

  client.connect(broker, broker_port, keep_alive_broker)
  client.loop_forever()
except KeyboardInterrupt:
  print('\nCtrl+c pressionado, encerrando aplicacao e saindo...')
  sys.exit(0)

 