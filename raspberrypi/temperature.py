import sys
import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO
import Adafruit_DHT

LED_PIN_NUMBER = 27

# BROKER properties
BROKER = 'iot.eclipse.org'
BROKER_PORT = 1883
KEEP_ALIVE_BROKER = 60
TOPIC = '29asoIoTMIL'

GPIO.setmode(GPIO.BCM) 
GPIO.setup(LED_PIN_NUMBER, GPIO.OUT)

def led_on():
  GPIO.output(LED_PIN_NUMBER, GPIO.HIGH)

def led_off():
  GPIO.output(LED_PIN_NUMBER, GPIO.LOW)

def on_connect(client, userdata, flags, rc):
  print('Conectado ao BROKER: ' + str(rc))
  client.subscribe(TOPIC)

def on_message(client, userdata, msg):
  payload = str(msg.payload.decode('utf-8'))
  print('Mensagem recebida: ' + payload)
  if (payload == 'ON'):
    led_on()
  elif (payload == 'OFF'):
    led_off()
  
try:

  while True:
    humidity, temperature = Adafruit_DHT.read_retry(11, 4)
    print('Temp: {0:0.1f} C  Humidity: {1:0.1f} %'.format(temperature, humidity))

  # MQTT Client to control LED status ON/OFF
  client = mqtt.Client()
  client.on_connect = on_connect
  client.on_message = on_message

  client.connect(BROKER, BROKER_PORT, KEEP_ALIVE_BROKER)
  client.loop_forever()
except KeyboardInterrupt:
  print('\nCtrl+c pressionado, encerrando aplicacao...')
  sys.exit(0)

 