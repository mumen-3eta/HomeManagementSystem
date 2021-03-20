const boom = require('@hapi/boom');

const { getAllUsersForAdmin } = require('../../database/queries');

const getAllUsers = async (req, res, next) => {
  try {

    const { rows: productionData } = await getAllUsersForAdmin();

    return res.status(201).json({
      title: 'all users data',
      detail: 'all users data collected Successfully ',
      productionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllUsers;
