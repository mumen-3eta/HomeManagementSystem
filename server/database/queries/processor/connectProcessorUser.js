const { connection } = require('../../data/index');

const connectProcessorUser = (userId, ProcessorId) => {
  const sql = {
    text:
      'insert into user_processor(processor_id,user_id) values(ProcessorId,userId) returning * ;',
    values: [userId, ProcessorId],
  };
  return connection.query(sql);
};

module.exports = connectProcessorUser;
