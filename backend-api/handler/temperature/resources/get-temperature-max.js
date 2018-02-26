const _ = require('lodash/fp');
const { Firebase, Options } = require('../../../initializer/firebase');
const Response = require('../../../lib/response');
const Resolve = require('../../../lib/resolve');

const getTemperatureMax = () => {
  Firebase.initializeApp(Options);
  return Firebase.database().ref('temperature/max').once('value')
    .then(snapshot => snapshot.val());
};

const handler = (event, context, callback) => {
  return getTemperatureMax()
    .then(max => ({ max: max }))
    .then(Response.success)
    .then(Resolve.success(callback))
    .catch(Resolve.propagate(callback, Response.failure));  
};

module.exports = { handler };