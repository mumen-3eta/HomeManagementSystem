const { connection } = require('../../../data');

const getControllerTypeByLabel = ({ locationLabel }) => {
  const sql = {
    text: 'select * from controller_location where label = $1;',
    values: [locationLabel],
  };
  return connection.query(sql);
};

module.exports = getControllerTypeByLabel;
