const createControllerForProduction = require('./createControllerForProduction');
const getAllControllerProduction = require('./getAllControllerProduction');
const connectControllerToProcessor = require('./connectControllerToProcessor');
const changeControllerStatus = require('./changeControllerStatus');
const getAllProcessorControllers = require('./getAllProcessorControllers');
const getAllUserControllers = require('./getAllUserControllers');
const deleteControllerConnection = require('./deleteControllerConnection');

module.exports = {
  createControllerForProduction,
  getAllControllerProduction,
  connectControllerToProcessor,
  changeControllerStatus,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection
};
