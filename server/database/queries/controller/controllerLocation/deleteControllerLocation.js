const { connection } = require('../../../data');

const deleteControllerLocation = (id) => {
  const sql = {
    text: 'delete from controller_location where id = $1;',
    values: [id],
  };
  return connection.query(sql);
};

module.exports = deleteControllerLocation;
