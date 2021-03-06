const jwt = require('jsonwebtoken');

const sign = (payload) =>
  new Promise((resolve, reject) => {
    jwt.sign(payload, process.env.SECRET, (err, token) => {
      if (err) {
        return reject(err);
      }
      return resolve(token);
    });
  });

const verify = (token) =>
  new Promise((resolve, reject) => {
    jwt.verify(token, process.env.SECRET, (err, decoded) => {
      if (err) {
        return reject(err);
      }
      return resolve(decoded);
    });
  });

module.exports = { sign, verify };
