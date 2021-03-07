const { connection } = require('../../data/index');

const checkIdByEmailOrUserName = ({ email, userName }) => {
  let text = 'SELECT id, password FROM users where ';
  const values = [];
  if (email) {
    values.push(email);
    text += 'email = $1 ;';
  } else if (userName) {
    values.push(userName);
    text += `user_name = $1 ;`;
  }
  const sql = {
    text,
    values,
  };
  return connection.query(sql);
};

module.exports = checkIdByEmailOrUserName;
