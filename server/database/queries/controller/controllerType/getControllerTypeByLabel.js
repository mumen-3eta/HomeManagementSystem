const { connection } = require('../../../data');

const getControllerTypeByLabel = ({ typeLabel }) => {
  const sql = {
    text: 'select * from controller_type where label = $1;',
    values: [typeLabel],
  };
  return connection.query(sql);
};

module.exports = getControllerTypeByLabel;
