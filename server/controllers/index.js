const { clientError, serverError } = require('./errorHandle');
const { addUsers, authUser } = require('./users');
const { swaggerServe, swaggerSetup } = require('./swagger');

module.exports = {
  clientError,
  serverError,
  addUsers,
  authUser,
  swaggerServe,
  swaggerSetup,
};
