const { connection } = require('../../data/index');

const createControllerProduction = () => {
  const sql =
    'insert into controller_production(create_at) values(CURRENT_TIMESTAMP) returning id ;';
  return connection.query(sql);
};

module.exports = createControllerProduction;
