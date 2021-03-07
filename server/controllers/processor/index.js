const createProcessorForProduction = require('./createProcessorProduction');
const connectProcessorToUser = require('./connectProcessorToUser');
const getProcessorConnection = require('./getProcessorConnection');
const deleteProcessorConnection = require('./deleteProcessorConnection');

module.exports = {
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
};
