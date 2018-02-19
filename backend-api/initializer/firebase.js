const Firebase = require('firebase-admin');
const Credentials = require('./firebase_credentials.json')[process.env.STAGE];
const Options = {
  credential: Firebase.credential.cert(Credentials),
  databaseURL: Credentials.databaseURL
};

module.exports = { Firebase, Options };
