const boom = require('@hapi/boom');

const { getAllControllerProductionForAdmin } = require('../../../database/queries');

const getAllControllerProduction = async (req, res, next) => {
  try {

    const { rows: productionData } = await getAllControllerProductionForAdmin();

    return res.status(201).json({
      title: 'Controller production data',
      detail: 'Controller units production data collected Successfully ',
      productionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllControllerProduction;
