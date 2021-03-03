const createControllerProduction = require('./createControllerProduction');
const connectControllerProcessor = require('./connectControllerProcessor');
const updateUserControllerData = require('./updateUserControllerData');
const deleteControllerConnection = require('./deleteControllerConnection');
const {
  addControllerType,
  getControllerType,
  updateControllerType,
  deleteControllerType,
} = require('./controllerType');
const {
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
} = require('./controllerLocation');

module.exports = {
  createControllerProduction,
  connectControllerProcessor,
  updateUserControllerData,
  deleteControllerConnection,
  addControllerType,
  getControllerType,
  updateControllerType,
  deleteControllerType,
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
};
