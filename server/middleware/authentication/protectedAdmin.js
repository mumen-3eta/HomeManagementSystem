const boom = require('@hapi/boom');

const { verify } = require('../../utils/jwt');

const protectedAdmin = async (req, res, next) => {
  const { token } = req.cookies;

  try {
    const { isAdmin } = await verify(token);
    if (isAdmin) {
      next();
    } else {
      next(boom.unauthorized());
    }
  } catch (error) {
    if (error.name === 'JsonWebTokenError') {
      res.clearCookie('token');
      next(boom.unauthorized());
    } else {
      next(error);
    }
  }
};

module.exports = protectedAdmin;
