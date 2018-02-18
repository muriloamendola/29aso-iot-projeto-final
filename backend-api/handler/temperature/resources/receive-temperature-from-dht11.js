const _ = require('lodash/fp');
const firebase = require('../../../lib/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');

const temperatureKey = () => {
  const today = new Date();
  return `${today.getFullYear()}${today.getMonth()+1}${today.getDate()}`;
};

const getTemperatureMarkingsForToday = () => {
  return firebase.database().ref(`/temperature/${temperatureKey()}`).once('value')
    .then(snapshot => snapshot.val());
};

const saveTodaysTemperaturesMarkings = todaysTemperaturesMarkings => {
  return firebase.database().ref(`/temperature/${temperatureKey()}`).set(todaysTemperaturesMarkings);
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
    .catch(Resolve.propagate(callback, Response.badRequest));
  
};

module.exports = { handler };