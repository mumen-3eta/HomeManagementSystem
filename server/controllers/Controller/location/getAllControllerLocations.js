const boom = require('@hapi/boom');

const { getAllControllerLocation } = require('../../../database/queries');

const getAllControllerTypes = async (req, res, next) => {
  try {
    const { rows: locationLabels } = await getAllControllerLocation();

    return res.status(201).json({
      title: 'All Controller location',
      detail: 'Controller location fetched Successfully',
      locationLabels,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllControllerTypes;
