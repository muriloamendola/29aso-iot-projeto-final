const Moment = require('moment-timezone');
const UNSPECIFIED_ERROR = 'Please specify an error.';

const timestamp = () => Moment().tz(process.env.DEFAULT_TIMEZONE).valueOf();

const error = (err = new Error(UNSPECIFIED_ERROR)) => {
  if (err instanceof Error) {
    const { name, message, stack } = err;
    const formatted = JSON.stringify({ timestamp: timestamp(), message, stack: stack.split('\n') });
    console.error(name, formatted);
  }
  console.error(err);
};

const warn = (content) => console.warn('WARN', content);

module.exports = { error, warn };
