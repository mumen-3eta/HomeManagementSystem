const boom = require('@hapi/boom');

const { getUserDataById } = require('../../database/queries');
const { verify } = require('../../utils/jwt');

const verifyUser = async (req, res, next) => {
  const { token } = req.cookies;
  if (!token) {
    return next(boom.unauthorized());
  }
  try {
    const { userId, isAdmin } = await verify(token);
    const {
      rows: [{ is_admin: isAdminUser }],
    } = await getUserDataById({ userId });
    if (isAdmin !== isAdminUser) {
      res.clearCookie('token');
      return next(boom.unauthorized());
    }
    req.userId = userId;
    return next();
  } catch (error) {
    if (error.name === 'JsonWebTokenError') {
      res.clearCookie('token');
      return next(boom.unauthorized());
    }
    return next(boom.badImplementation(error));
  }
};

module.exports = verifyUser;
