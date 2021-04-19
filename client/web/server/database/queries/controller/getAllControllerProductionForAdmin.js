const { connection } = require('../../data/index');

const getAllControllerProductionForAdmin = () => {
  const sql = 'select * from controller_production ;'
  return connection.query(sql);
};

module.exports = getAllControllerProductionForAdmin;
