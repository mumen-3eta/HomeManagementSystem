const boom = require('@hapi/boom');

const { getAllProcessorProductionForAdmin } = require('../../database/queries');

const getAllProcessorProduction = async (req, res, next) => {
  try {

    const { rows: productionData } = await getAllProcessorProductionForAdmin();

    return res.status(201).json({
      title: 'processor production data',
      detail: 'processor unit production data collected Successfully ',
      productionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllProcessorProduction;
