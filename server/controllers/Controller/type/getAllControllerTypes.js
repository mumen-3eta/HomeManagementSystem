const boom = require('@hapi/boom');

const { getAllControllerType } = require('../../../database/queries');

const getAllControllerTypes = async (req, res, next) => {
  try {
    const { rows: typeLabels } = await getAllControllerType();

    return res.status(201).json({
      title: 'All Controller Type',
      detail: 'Controller Type fetched Successfully',
      typeLabels,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllControllerTypes;
