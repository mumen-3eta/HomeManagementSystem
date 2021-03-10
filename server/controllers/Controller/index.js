const {
  createControllerForProduction,
  connectControllerToProcessor,
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
  connectControllerToProcessor,
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
