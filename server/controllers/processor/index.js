const createProcessorForProduction = require('./createProcessorProduction');
const connectProcessorToUser = require('./connectProcessorToUser');
const getProcessorConnection = require('./getProcessorConnection');
const deleteProcessorConnection = require('./deleteProcessorConnection');
const getAllProcessorProduction = require('./getAllProcessorProduction')

module.exports = {
  createProcessorForProduction,
  getAllProcessorProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
};
