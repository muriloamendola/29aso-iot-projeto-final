# 29aso-iot-projeto-final
> Projeto construído como solução final para matéria de Arquitetura em Plataforma Mobile & IoT do MBA em Arquitetura de Soluções da faculdade FIAP

## Objetivo

O objetivo é a contrução de um projeto prático simples que utilize todos os recursos abordados em sala de aula. 

Optamos por desenvolver um protótipo onde a temperatura e humidade ambiente serão medidas por um sensor e enviadas para uma base de dados em cloud através de uma chamada a uma API REST. 

O serviço que recebe as medições de temperatura verificará se a mesma excedeu o limite configurado e enviará uma mensagem a um tópico do `broker mosquitto`. Caso a temperatura seja maior ou igual ao limite configurado a mensagem será `ON` e caso contrário `OFF`.

Há um código para controle do led que através de um `subscribe` no tópico acima mecionado, recebe as mensagens que chegam ao tópico e acende o led quando recebe uma mensagem `ON` ou apaga quando recebe a mensagem `OFF`.

A ideia é bem simples e foi pensado única e exclusivamente com o intuito de utilizar todas as tecnologias e protocolos vistos em aula.

![Protótipo da solução final](./doc/images/prototipo_montado.jpeg)

## Solução

Nessa seção explicaremos em detalhes as tecnologias e equipamentos utilizados no desenvolvimento da solução.

### Equipamentos utilizados

* 1 | [Raspberry Pi 3B](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/)
![Raspberry Pi 3B](./doc/images/raspberrypi_3.jpg)
* 1 | [Sensor Dht11](https://medium.com/dyi-electronics/raspberry-pi-and-dht11-humidity-sensor-cccf6b3072ad)
![Dht11](./doc/images/dht11.jpeg)
* 1 | Breadboard Modular
![Breadboard Modular](./doc/images/breadboard_modular.jpg)
* 1 | Led
![Led](./doc/images/led.jpg)
* 2 | Resistor
![Resistor](./doc/images/resistor.jpeg)
* 4 | Jumpers
![Jumpers](./doc/images/jumpers.jpeg)

* paho-mqtt

# Python library to read the DHT series of humidity and temperature sensors on a Raspberry Pi or Beaglebone Black.
```bash
git clone https://github.com/adafruit/Adafruit_Python_DHT.git
sudo python setup.py install
```

## Backend API

https://platform.serverless.com/services/mcamendola/backend-api-29aso-iot

