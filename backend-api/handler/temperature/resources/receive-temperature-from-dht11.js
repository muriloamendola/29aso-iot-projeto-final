const _ = require('lodash/fp');
const firebase = require('../../../lib/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');
const KeyHelper = require('../helpers/firebase-temperature-key-helper');
const MQTT = require('../../../lib/mqtt-api');

const getTemperatureMarkingsForToday = () => {
  return firebase.database().ref(`/temperature/${KeyHelper.buildKey(new Date())}`).once('value')
    .then(snapshot => snapshot.val());
};

const getTemperatureMax = () => {
  return firebase.database().ref('temperature/max').once('value')
    .then(snapshot => snapshot.val());
};

const saveTodaysTemperaturesMarkings = todaysTemperaturesMarkings => {
  return firebase.database().ref(`/temperature/${KeyHelper.buildKey(new Date())}`).set(todaysTemperaturesMarkings);
};

const handler = (event, context, callback) => {
  const temperatureMarking = JSON.parse(event.body);
  return Promise.all([
      Promise.resolve(temperatureMarking),
      getTemperatureMarkingsForToday(),
      getTemperatureMax()
    ])
    .then(([temperatureMarking, todaysTemperaturesMarkings, temperatureMax]) => {
      if (_.isEmpty(todaysTemperaturesMarkings)) todaysTemperaturesMarkings = { temperatures: [] };
      todaysTemperaturesMarkings.temperatures.push(temperatureMarking);
      
      MQTT.publish(temperatureMarking.temperature >= temperatureMax ? 'ON' : 'OFF');

      return todaysTemperaturesMarkings;
    })
    .then(saveTodaysTemperaturesMarkings)
    .then(Response.created)
    .then(Resolve.success(callback))
    .catch(Resolve.propagate(callback, Response.failure));  
};

module.exports = { handler };