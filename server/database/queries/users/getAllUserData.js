const { connection } = require('../../data/index');

const getAllUserData = ({ userId }) => {
  const sql = {
    text:
      'select user_name, email, first_name, last_name, image, mobile from users where id = $1;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getAllUserData;
