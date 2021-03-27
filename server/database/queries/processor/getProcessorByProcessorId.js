const { connection } = require('../../data/index');

const getProcessorByProcessorId = ({ processorId }) => {
  const sql = {
    text: 'select * from user_processor where processor_id = $1;',
    values: [processorId],
  };
  return connection.query(sql);
};

module.exports = getProcessorByProcessorId;
