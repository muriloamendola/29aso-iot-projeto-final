const _ = require('lodash/fp');
const firebase = require('../../../lib/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');

const saveTemperatureMax = temperature => {
  return firebase.database().ref('/temperature/max').set(temperature);
};

const handler = (event, context, callback) => {
  const temperature = JSON.parse(event.body);
  return Promise.resolve(temperature)
    .then(_.get('max'))
    .then(saveTemperatureMax)
    .then(Response.success)
    .then(Resolve.success(callback))
    .catch(Resolve.propagate(callback, Response.failure));  
};

module.exports = { handler };