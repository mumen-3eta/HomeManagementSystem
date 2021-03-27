const { connection } = require('../../data/index');

const getControllerByControllerId = ({ controllerId }) => {
  const sql = {
    text: 'select * from user_controller where controller_id = $1;',
    values: [controllerId],
  };
  return connection.query(sql);
};

module.exports = getControllerByControllerId;
