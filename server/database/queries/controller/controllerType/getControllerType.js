const { connection } = require('../../../data');

const getControllerType = (id) => {
  const sql = {
    text: 'select * from controller_type;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = getControllerType;
