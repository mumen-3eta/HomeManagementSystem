const { clientError, serverError } = require('./errorHandle');

const {
  addUsers,
  authUser,
  login,
  getAllProfileData,
  updateProfileData,
} = require('./users');

const {
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
} = require('./processor');

const {
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
} = require('./Controller');

const { swaggerServe, swaggerSetup } = require('./swagger');

module.exports = {
  clientError,
  serverError,
  addUsers,
  login,
  authUser,
  getAllProfileData,
  updateProfileData,
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
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
  // createControllerForProduction,
  swaggerServe,
  swaggerSetup,
};
