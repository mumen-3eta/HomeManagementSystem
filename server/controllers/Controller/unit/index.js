const createControllerForProduction = require('./createControllerForProduction');
const connectControllerToProcessor = require('./connectControllerToProcessor');
const getAllProcessorControllers = require('./getAllProcessorControllers');
const getAllUserControllers = require('./getAllUserControllers');
const deleteControllerConnection = require('./deleteControllerConnection');

module.exports = {
  createControllerForProduction,
  connectControllerToProcessor,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection
};
