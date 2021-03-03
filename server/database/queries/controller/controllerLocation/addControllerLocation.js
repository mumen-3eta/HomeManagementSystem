const { connection } = require('../../../data');

const addControllerLocation = (label) => {
  const sql = {
    text: 'insert into controller_location(label) values($1) returning *;',
    values: [label],
  };
  return connection.query(sql);
};

module.exports = addControllerLocation;
