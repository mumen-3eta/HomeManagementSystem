const { connection } = require('../../data/index');

const createProcessorProduction = () => {
  const sql =
    'insert into processor_production(create_at) values(CURRENT_TIMESTAMP) returning id ;';
  return connection.query(sql);
};

module.exports = createProcessorProduction;
