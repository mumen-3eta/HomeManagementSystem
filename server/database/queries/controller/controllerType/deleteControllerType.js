const { connection } = require('../../../data');

const deleteControllerType = ({ typeId }) => {
  const sql = {
    text: 'delete from controller_type where id = $1;',
    values: [typeId],
  };
  return connection.query(sql);
};

module.exports = deleteControllerType;
