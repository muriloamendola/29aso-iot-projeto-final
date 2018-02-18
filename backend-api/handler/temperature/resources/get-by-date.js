const firebase = require('../../../lib/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');
const KeyHelper = require('../helpers/firebase-temperature-key-helper');

const getTemperaturesByDate = date => {
  return firebase.database().ref(`/temperature/${KeyHelper.buildKey(date)}`).once('value')
    .then(snapshot => snapshot.val());
}

const handler = (event, context, callback) => {
  const date = new Date(parseInt(event.pathParameters.date));

  return Promise.resolve(date)
    .then(getTemperaturesByDate)
    .then(Response.success)
    .then(Resolve.success(callback))
    .catch(Resolve.propagate(callback, Response.failure));  
};

module.exports = { handler };