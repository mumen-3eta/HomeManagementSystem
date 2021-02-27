const { readFileSync } = require('fs');
const { join } = require('path');
const connection = require('./connection');

const dbBuild = () => {
  let sql = readFileSync(join(__dirname, 'hmsy.sql')).toString();
  if (process.env.NODE_ENV === 'test') {
    sql += readFileSync(join(__dirname, 'hmsyFake.sql')).toString();
  }
  return connection.query(sql);
};

module.exports = dbBuild;
