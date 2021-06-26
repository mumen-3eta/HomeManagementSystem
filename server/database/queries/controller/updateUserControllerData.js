const connection = require('../../data');

const updateUserControllerData = (data) => {
  const { name, processorId, typeId, locationId, controllerId } = data;
  const sql = {
    text:
      'update user_controller set name=COALESCE($1,name ) ,processorId =COALESCE($2,processorId) ,typeId =COALESCE($3,typeId) ,locationId=COALESCE($4,locationId) where controller_id=$5 or name = $1 returning *;', // added the or to make it easer for the user to find it
    values: [name, processorId, typeId, locationId, controllerId],
  };
  return connection.query(sql);
};

module.exports = updateUserControllerData;
