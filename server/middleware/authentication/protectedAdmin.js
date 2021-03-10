const boom = require('@hapi/boom');

const { verify } = require('../../utils/jwt');

const protectedAdmin = async (req, res, next) => {
  const { token } = req.cookies;

  try {
    const { isAdmin } = await verify(token);
    if (!isAdmin) {
      next(
        boom(401, 'Unauthorized Error', "Unauthenticated, your aren't admin")
      );
    }
    next();
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
