const { connection } = require('../../data/index');

const getAllUserData = (id) => {
  const sql = {
    text:
      'select user_name, email, first_name, last_name, image, mobile from users where id = $1;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = getAllUserData;
