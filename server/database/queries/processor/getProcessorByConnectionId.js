const { connection } = require('../../data/index');

const getProcessorByConnectionId = ({ processorId }) => {
  const sql = {
    text: 'select * from user_processor where id = $1;',
    values: [processorId],
  };
  return connection.query(sql);
};

module.exports = getProcessorByConnectionId;
