const { connection } = require('../../../data');

const addControllerType = ({ label }) => {
  const sql = {
    text: 'insert into controller_type(label) values($1) returning *;',
    values: [label],
  };
  return connection.query(sql);
};

module.exports = addControllerType;
