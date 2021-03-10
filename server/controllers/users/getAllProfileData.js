const boom = require('@hapi/boom');

const { getAllUserData } = require('../../database/queries');

const getAllProfileData = async (req, res, next) => {
  try {
    const { userId } = req;
    const { rows: profileData } = await getAllUserData({ userId });

    return res.status(201).json({
      title: 'User profile data',
      detail: 'Successfully data collected',
      profileData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllProfileData;
