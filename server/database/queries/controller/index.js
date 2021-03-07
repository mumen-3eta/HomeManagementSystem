const createControllerProduction = require('./createControllerProduction');
const connectControllerProcessor = require('./connectControllerProcessor');
const updateUserControllerData = require('./updateUserControllerData');
const deleteControllerConnection = require('./deleteControllerConnection');
const getControllerByControllerId = require('./getControllerByControllerId');

const {
  addControllerType,
  getControllerType,
  getControllerTypeByLabel,
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
  getControllerByControllerId,
  addControllerType,
  getControllerType,
  getControllerTypeByLabel,
  updateControllerType,
  deleteControllerType,
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
};
