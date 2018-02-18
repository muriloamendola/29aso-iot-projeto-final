const _ = require('lodash/fp');
const firebase = require('../../../lib/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');
const KeyHelper = require('../helpers/firebase-temperature-key-helper');

const getTemperatureMarkingsForToday = () => {
  return firebase.database().ref(`/temperature/${KeyHelper.buildKey(new Date())}`).once('value')
    .then(snapshot => snapshot.val());
};

const saveTodaysTemperaturesMarkings = todaysTemperaturesMarkings => {
  return firebase.database().ref(`/temperature/${KeyHelper.buildKey(new Date())}`).set(todaysTemperaturesMarkings);
};

const handler = (event, context, callback) => {
  const temperatureMarking = JSON.parse(event.body);
  return Promise.all([
      Promise.resolve(temperatureMarking),
      getTemperatureMarkingsForToday()
    ])
    .then(([temperatureMarking, todaysTemperaturesMarkings]) => {
      if (_.isEmpty(todaysTemperaturesMarkings)) todaysTemperaturesMarkings = { temperatures: [] };
      todaysTemperaturesMarkings.temperatures.push(temperatureMarking);

      return todaysTemperaturesMarkings;
    })
    .then(saveTodaysTemperaturesMarkings)
    .then(Response.created)
    .then(Resolve.success(callback))
    .catch(Resolve.propagate(callback, Response.failure));  
};

module.exports = { handler };