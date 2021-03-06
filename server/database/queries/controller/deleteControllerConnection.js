const { connection } = require('../../data/index');

const deleteControllerConnection = (userControllerID) => {
  const sql = {
    text: 'delete from user_processor where controller_id = $1 ;',
    values: [userControllerID],
  };
  return connection.query(sql);
};

module.exports = deleteControllerConnection;
