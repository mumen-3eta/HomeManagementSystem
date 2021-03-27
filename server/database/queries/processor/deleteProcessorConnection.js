const { connection } = require('../../data/index');

const deleteProcessorConnectionData = ({ userId, processorId }) => {
  const sql = {
    text:
      'delete from user_processor where user_id = $1 and processor_id = $2;',
    values: [userId, processorId],
  };
  return connection.query(sql);
};

module.exports = deleteProcessorConnectionData;
