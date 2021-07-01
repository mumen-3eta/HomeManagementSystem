const { connection } = require('../../../data');

const getAllControllerType = () => {
  const sql = 'select * from controller_type;';
  return connection.query(sql);
};

module.exports = getAllControllerType;
