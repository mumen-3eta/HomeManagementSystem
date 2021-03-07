const { connection } = require('../../../data');

const getControllerLocation = ({ locationId }) => {
  const sql = {
    text: 'select * from controller_location where id = $1 ;',
    values: [locationId],
  };
  return connection.query(sql);
};

module.exports = getControllerLocation;
