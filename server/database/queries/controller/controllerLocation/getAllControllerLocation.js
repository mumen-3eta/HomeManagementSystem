const { connection } = require('../../../data');

const getAllControllerType = () => {
  const sql = 'select * from controller_location;';
  return connection.query(sql);
};

module.exports = getAllControllerType;
