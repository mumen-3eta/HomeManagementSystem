const { connection } = require('../../data/index');

const deleteControllerConnection = ({ controllerId, processorId }) => {
  const sql = {
    text:
      'delete from user_controller where controller_id = $1 and processor_id = $2 ;',
    values: [controllerId, processorId],
  };
  return connection.query(sql);
};

module.exports = deleteControllerConnection;
