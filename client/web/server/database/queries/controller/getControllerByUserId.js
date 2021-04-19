const { connection } = require('../../data/index');

const getControllerByUserId = ({ userId }) => {
  const sql = {
    text:
      'select * from user_controller where processor_id in (select id from user_processor where user_id = $1) ;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getControllerByUserId;
