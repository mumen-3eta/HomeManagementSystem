const { connection } = require('../../data/index');

const getAllUsersForAdmin = () => {
  const sql = 'select id, user_name, email, first_name, last_name, image, mobile, is_admin, create_at from users ;';
  return connection.query(sql);
};

module.exports = getAllUsersForAdmin;