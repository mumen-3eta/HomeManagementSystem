const { connection } = require('../../data/index');

const getControllerStatus = ({ controllerId,newStatus }) => {
  const sql = {
    text:
      'update user_controller set status=COALESCE($1,status ) where controller_id = $2 returning status ;',
    values: [newStatus,controllerId],
  };
  return connection.query(sql);
};

module.exports = getControllerStatus;
