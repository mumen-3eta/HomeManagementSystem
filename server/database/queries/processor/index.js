const createProcessorProduction = require('./createProcessorProduction');
const connectProcessorUser = require('./connectProcessorUser');
const getProcessorConnectionByUserId = require('./getProcessorConnectionByUserId');
const getProcessorByProcessorId = require('./getProcessorByProcessorId');
const deleteProcessorConnectionData = require('./deleteProcessorConnection');
const getProcessorByConnectionId = require('./getProcessorByConnectionId');
const getAllProcessorProductionForAdmin = require('./getAllProcessorProductionForAdmin');

module.exports = {
  createProcessorProduction,
  connectProcessorUser,
  getProcessorConnectionByUserId,
  getProcessorByProcessorId,
  deleteProcessorConnectionData,
  getProcessorByConnectionId,
  getAllProcessorProductionForAdmin,
};
