const { connection } = require('../../data/index');

const connectProcessorUser = ({ userId, processorId }) => {
  const sql = {
    text:
      'insert into user_processor(processor_id,user_id) values($2,$1) returning * ;',
    values: [userId, processorId],
  };
  return connection.query(sql);
};

module.exports = connectProcessorUser;
