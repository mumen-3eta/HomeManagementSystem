const { connection } = require('../../data/index');

const getProcessorConnectionByUserId = (userId) => {
  const sql = {
    text: 'select * from user_processor where user_id = $1;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getProcessorConnectionByUserId;
