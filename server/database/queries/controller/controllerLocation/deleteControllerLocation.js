const { connection } = require('../../../data');

const deleteControllerLocation = ({ locationId }) => {
  const sql = {
    text: 'delete from controller_location where id = $1;',
    values: [locationId],
  };
  return connection.query(sql);
};

module.exports = deleteControllerLocation;
