const { Firebase, Options } = require('../initializer/firebase');

const initializeApp = () => {
  if (!Firebase.apps.length) {
    Firebase.initializeApp(Options);
  }
}

const once = ref => {
  initializeApp();
  return Firebase.database().ref(ref).once('value')
    .then(snapshot => snapshot.val())
    .catch((err) => {
      Firebase.app().delete();
      throw err;
    });
}

const set = (ref, value) => {
  initializeApp();
  return Firebase.database().ref(ref).set(value)
    .catch((err) => {
      Firebase.app().delete();
      throw err;
    });
}

module.exports = { once, set };