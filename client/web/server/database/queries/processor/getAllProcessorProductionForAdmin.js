const { connection } = require('../../data/index');

const getAllProcessorProductionForAdmin = () => {
  const sql = 'select * from processor_production ;'
  return connection.query(sql);
};

module.exports = getAllProcessorProductionForAdmin;
