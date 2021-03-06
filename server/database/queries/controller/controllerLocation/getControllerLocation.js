const { connection } = require('../../../data');

const getControllerLocation = (id) => {
  const sql = {
    text: 'select * from controller_location;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = getControllerLocation;
