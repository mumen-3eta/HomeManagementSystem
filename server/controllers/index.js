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
  swaggerServe,
  swaggerSetup,
};
