const { connection } = require('../../data/index');

const getControllerByUserId = ({ userId }) => {
  const sql = {
    text:
      // 'select * from user_controller where processor_id in (select id from user_processor where user_id = $1) ;',
      'select user_controller.*,controller_type.label as type_label, controller_location.label as location_label from user_controller left join controller_type on controller_type.id = user_controller.typeId left join controller_location on controller_location.id = user_controller.location_id where processor_id in (select id from user_processor where user_id = $1) ;',
    values: [userId],
  };
  return connection.query(sql);
};

module.exports = getControllerByUserId;
