const _ = require('lodash');
const Logger = require('./logger');

const success = _.curry((fn, body) => fn(null, body));

const error = _.curry((fn, err) => {
  Logger.error(err);
  return fn(err);
});

const propagate = _.curry((fn, response, err) => {
  const message = err instanceof Error ? err.message : err;
  Logger.error(err);
  return fn(null, response(message));
});

module.exports = { success, error, propagate };
