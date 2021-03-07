const { connection } = require('../../data');

const getUserDataById = ({ userId }) => {
  const sql = {
    text: 'SELECT email, userName, image, isAdmin FROM users where id = $1 ;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getUserDataById;
