const { connection } = require('../../data/index');

const getControllerStatus = ({ controllerId }) => {
  const sql = {
    text:
      'select status from user_controller where controller_id = $1 ;',
    values: [controllerId],
  };
  return connection.query(sql);
};

module.exports = getControllerStatus;
