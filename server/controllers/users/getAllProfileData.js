const boom = require('@hapi/boom');

const { getAllUserData } = require('../../database/queries');

const getAllProfileData = async (req, res, next) => {
  try {
    // dont forget to un comment the next line later---------------------------------------------------
    // const { userId } = req;
    const userId = 1;
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
