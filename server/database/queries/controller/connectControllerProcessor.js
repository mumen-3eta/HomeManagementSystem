const { connection } = require('../../data/index');

const connectControllerProcessor = ({
  name,
  processorId,
  typeId,
  locationId,
  controllerId,
}) => {
  const sql = {
    text:
      'insert into user_controller(name, processor_id,typeId,location_id,controller_id) values($1,$2,$3,$4,$5) returning * ;',
    values: [name, processorId, typeId, locationId, controllerId],
  };
  return connection.query(sql);
};

module.exports = connectControllerProcessor;
