const { connection } = require('../../../data');

const getControllerType = ({ typeId }) => {
  const sql = {
    text: 'select * from controller_type where id = $1;',
    values: [typeId],
  };
  return connection.query(sql);
};

module.exports = getControllerType;
