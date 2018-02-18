const firebase = require('firebase');

const config = {
  apiKey: "AIzaSyDxHqdZ_cA29Ga-md-mVsN3eL9fffSyRbM",
  authDomain: "aso-iot-projeto-final.firebaseapp.com",
  databaseURL: "https://aso-iot-projeto-final.firebaseio.com",
  projectId: "aso-iot-projeto-final",
  storageBucket: "aso-iot-projeto-final.appspot.com",
  messagingSenderId: "523886072239"
};

if (!firebase.apps.length) {
  firebase.initializeApp(config);
}

module.exports = firebase;