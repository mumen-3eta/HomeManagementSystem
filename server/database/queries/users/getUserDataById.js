const { connection } = require('../../data');

const getUserDataById = ({ userId }) => {
  const sql = {
    text: 'SELECT id, email, user_name, is_admin FROM users where id = $1 ;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getUserDataById;
