const { Firebase, Options } = require('../initializer/firebase');

const initializeApp = () => {
  return Firebase.initializeApp(Options);
}

const once = ref => {
  let app = initializeApp();
  return Firebase.database(app).ref(ref).once('value')
    .then(snapshot => {
      console.log(`once.(${ref})`, snapshot.val());
      app.delete();
      return snapshot.val()
    })
    .catch((err) => {
      console.log(`once.(${ref})`, err);
      app.delete();
      throw err;
    });
}

const set = (ref, value) => {
  let app = initializeApp();
  return Firebase.database(app).ref(ref).set(value)
    .then(() => {
      console.log(`set(${ref}) realizado com sucesso.`, value)
      app.delete();
    })
    .catch((err) => {
      console.log(`once.(${ref})`, err);
      app.delete();
      throw err;
    });
}

module.exports = { once, set };