const boom = require('@hapi/boom');

const { getUserDataById } = require('../../database/queries');

const authUser = async (req, res, next) => {
  try {
    const { userId } = req;
    const {
      rows: [user],
    } = await getUserDataById(userId);
    if (!user) {
      return next(boom.conflict("The user isn't exist"));
    }

    return res.status(200).json({
      title: 'Authentication',
      detail: 'Successfully authenticated user',
      user,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = authUser;
