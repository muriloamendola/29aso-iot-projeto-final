import sys
import Adafruit_DHT
import time
import requests
import json

def send_data_to_service(temperature, humidity):
  service_url = 'https://15m3sva997.execute-api.us-east-1.amazonaws.com/dev/temperature'
  data = json.dumps({ 
    'temperature': temperature,
	  'humidity': humidity,
	  'markingAt': time.time()
  })
  response = requests.post(service_url, data)
  print(response.json())

try:
  while True:
    humidity, temperature = Adafruit_DHT.read_retry(11, 4)
    print('Temp: {0:0.1f} C  Humidity: {1:0.1f} %'.format(temperature, humidity))
    time.sleep(2)
except KeyboardInterrupt:
  print('\nCtrl+c pressionado, encerrando aplicacao...')
  sys.exit(0)

 