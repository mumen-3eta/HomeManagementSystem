const { connection } = require('../../../data');

const updateControllerType = ({ typeId, label }) => {
  const sql = {
    text: 'update controller_type set label = $1 where id = $2  returning *;',
    values: [label, typeId],
  };
  return connection.query(sql);
};

module.exports = updateControllerType;
