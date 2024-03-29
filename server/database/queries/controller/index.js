const createControllerProduction = require('./createControllerProduction');
const getAllControllerProductionForAdmin = require('./getAllControllerProductionForAdmin');
const connectControllerProcessor = require('./connectControllerProcessor');
const updateUserControllerData = require('./updateUserControllerData');
const deleteControllerConnection = require('./deleteControllerConnection');
const getControllerByControllerId = require('./getControllerByControllerId');
const changeControllerStatus = require('./changeControllerStatus');
const getControllerByProcessorId = require('./getControllerByProcessorId');
const getControllerByUserId = require('./getControllerByUserId');

const {
  addControllerType,
  getControllerType,
  getControllerTypeByLabel,
  updateControllerType,
  deleteControllerType,
  getAllControllerType,
} = require('./controllerType');
const {
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
  getAllControllerLocation,
  getControllerLocationByLabel,
} = require('./controllerLocation');

module.exports = {
  createControllerProduction,
  getAllControllerProductionForAdmin,
  connectControllerProcessor,
  updateUserControllerData,
  deleteControllerConnection,
  getControllerByControllerId,
  changeControllerStatus,
  getControllerByProcessorId,
  getControllerByUserId,
  addControllerType,
  getControllerType,
  getControllerTypeByLabel,
  updateControllerType,
  deleteControllerType,
  getAllControllerType,
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
  getAllControllerLocation,
  getControllerLocationByLabel,
};
