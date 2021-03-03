const { connection } = require('../../data/index');

const connectControllerProcessor = (data) => {
  const {processor_id} = data;
  const sql = {
    text:
      'insert into user_controller(processor_id,user_id) values(ProcessorId,userId) returning * ;',
    values: [],
  };
  return connection.query(sql);
};

module.exports = connectControllerProcessor;
