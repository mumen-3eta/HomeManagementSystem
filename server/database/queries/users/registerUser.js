const { connection } = require('../../data');

const registerUser = ({ email, userName, password }) => {
  const sql = {
    text:
      'INSERT INTO users ( email, user_name, password ) values($1 ,$2, $3) returning id;',
    values: [email, userName, password],
  };
  return connection.query(sql);
};

module.exports = registerUser;
