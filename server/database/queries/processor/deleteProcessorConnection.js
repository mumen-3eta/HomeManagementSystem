const { connection } = require('../../data/index');

const deleteProcessorConnection = (userId, ProcessorId) => {
  const sql = {
    text:
      'delete from user_processor where user_id = $1 and processor_id = $2;',
    values: [userId, ProcessorId],
  };
  return connection.query(sql);
};

module.exports = deleteProcessorConnection;
