const { clientError, serverError } = require('./errorHandle');
const { addUsers } = require('./users');
const { swaggerServe, swaggerSetup } = require('./swagger');

module.exports = {
  clientError,
  serverError,
  addUsers,
  swaggerServe,
  swaggerSetup,
};
