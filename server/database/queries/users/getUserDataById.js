const { connection } = require('../../data');

const getUserDataById = (id) => {
  const sql = {
    text: 'SELECT email, userName, image, isAdmin FROM users where id = $1 ;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = getUserDataById;
