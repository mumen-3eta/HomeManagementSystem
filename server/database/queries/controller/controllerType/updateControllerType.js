const { connection } = require('../../../data');

const updateControllerType = (id, label) => {
  const sql = {
    text: 'update controller_type set label = $1 where id = $2  returning *;',
    values: [label, id],
  };
  return connection.query(sql);
};

module.exports = updateControllerType;
