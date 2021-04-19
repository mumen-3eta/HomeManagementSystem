const {
  createControllerForProduction,
  getAllControllerProduction,
  connectControllerToProcessor,
  changeControllerStatus,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection,
} = require('./unit');

const {
  addControllerTypeLabel,
  updateControllerTypeLabel,
  deleteControllerTypeLabel,
  getAllControllerTypes,
} = require('./type');
const {
  addControllerLocationLabel,
  updateControllerLocationLabel,
  deleteControllerLocationLabel,
  getAllControllerLocations,
} = require('./location');

module.exports = {
  createControllerForProduction,
  getAllControllerProduction,
  connectControllerToProcessor,
  changeControllerStatus,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection,
  addControllerTypeLabel,
  updateControllerTypeLabel,
  deleteControllerTypeLabel,
  getAllControllerTypes,
  addControllerLocationLabel,
  updateControllerLocationLabel,
  deleteControllerLocationLabel,
  getAllControllerLocations,
};
