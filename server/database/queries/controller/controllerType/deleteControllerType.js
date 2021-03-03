const { connection } = require('../../../data');

const deleteControllerType = (id) => {
  const sql = {
    text: 'delete from controller_type where id = $1;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = deleteControllerType;
