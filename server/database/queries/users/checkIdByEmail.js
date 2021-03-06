const { connection } = require('../../data/index');

const signIn = (email) => {
  const sql = {
    text: 'SELECT id, password FROM users where email = $1 ;',
    values: [email],
  };
  return connection.query(sql);
};

module.exports = signIn;
