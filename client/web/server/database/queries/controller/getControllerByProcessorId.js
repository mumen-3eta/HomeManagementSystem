const { connection } = require('../../data/index');

const getControllerByProcessorId = ({ processorId }) => {
  const sql = {
    text: 'select * from user_controller where processor_id = $1;',
    values: [processorId],
  };
  return connection.query(sql);
};

module.exports = getControllerByProcessorId;
