const { connection } = require('../../../data');

const updateControllerLocation = ({ locationId, label }) => {
  const sql = {
    text:
      'update controller_location set label = $1 where id = $2  returning *;',
    values: [label, locationId],
  };
  return connection.query(sql);
};

module.exports = updateControllerLocation;
