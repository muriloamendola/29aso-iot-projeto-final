const _ = require('lodash/fp');
const { Firebase, Options } = require('../../../initializer/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');
const KeyHelper = require('../helpers/firebase-temperature-key-helper');
const MQTT = require('../../../lib/mqtt-api');

const getTemperatureMarkingsForToday = app => {
  return Firebase.database(app).ref(`/temperature/${KeyHelper.buildKey(new Date())}`).once('value')
    .then(snapshot => snapshot.val());
};

const getTemperatureMax = app => {
  return Firebase.database(app).ref('temperature/max').once('value')
    .then(snapshot => snapshot.val());
};

const saveTodaysTemperaturesMarkings = (app, todaysTemperaturesMarkings) => {
  return Firebase.database(app).ref(`/temperature/${KeyHelper.buildKey(new Date())}`).set(todaysTemperaturesMarkings);
};

const handler = (event, context, callback) => {
  const temperatureMarking = JSON.parse(event.body);

  let firebaseApp = Firebase.initializeApp(Options);

  return Promise.all([
      Promise.resolve(temperatureMarking),
      getTemperatureMarkingsForToday(firebaseApp),
      getTemperatureMax(firebaseApp)
    ])
    .then(([temperatureMarking, todaysTemperaturesMarkings, temperatureMax]) => {
      console.log('Nova marcação de temperatura', temperatureMarking);
      console.log(`Máxima temperatura: ${temperatureMax}`);

      if (_.isEmpty(todaysTemperaturesMarkings)) todaysTemperaturesMarkings = { temperatures: [] };
      todaysTemperaturesMarkings.temperatures.push(temperatureMarking);
      
      MQTT.publish(temperatureMarking.temperature >= temperatureMax ? 'ON' : 'OFF');

      return todaysTemperaturesMarkings;
    })
    .then(markings => {
      return saveTodaysTemperaturesMarkings(firebaseApp, markings)
        .then(() => Firebase.app().delete())
    })
    .then(Response.created)
    .then(Resolve.success(callback))
    .catch(error => {
      Firebase.app().delete();
      Resolve.propagate(callback, Response.failure, error);
    });  
};

module.exports = { handler };