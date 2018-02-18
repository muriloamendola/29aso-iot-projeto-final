import sys
import Adafruit_DHT
import time

try:
  while True:
    humidity, temperature = Adafruit_DHT.read_retry(11, 4)
    print('Temp: {0:0.1f} C  Humidity: {1:0.1f} %'.format(temperature, humidity))
    time.sleep(2)
except KeyboardInterrupt:
  print('\nCtrl+c pressionado, encerrando aplicacao...')
  sys.exit(0)

 