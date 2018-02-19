const _ = require('lodash/fp');
const FirebaseApi = require('../../../lib/firebase-api');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');

const saveTemperatureMax = temperature => {
  return FirebaseApi.set('/temperature/max', temperature);
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