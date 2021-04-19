const { connection } = require('../../data/index');

const deleteUser = (id) => {
  const sql = {
    text: 'delete from users where id = $1;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = deleteUser;
