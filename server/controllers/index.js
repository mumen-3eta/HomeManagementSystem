const { clientError, serverError } = require('./errorHandle');

const {
  addUsers,
  authUser,
  login,
  logout,
  getAllUsers,
  getAllProfileData,
  updateProfileData,
} = require('./users');

const {
  createProcessorForProduction,
  getAllProcessorProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
} = require('./processor');

const {
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
} = require('./Controller');

const { swaggerServe, swaggerSetup } = require('./swagger');

module.exports = {
  clientError,
  serverError,
  addUsers,
  login,
  logout,
  authUser,
  getAllUsers,
  getAllProfileData,
  updateProfileData,
  createProcessorForProduction,
  getAllProcessorProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
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
  swaggerServe,
  swaggerSetup,
};
