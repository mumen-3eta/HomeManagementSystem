const boom = require('@hapi/boom');

const { createProcessorProduction } = require('../../database/queries');

const createProcessorForProduction = async (req, res, next) => {
  try {
    const { rows: newProcessor } = await createProcessorProduction();

    return res.status(201).json({
      title: 'creating processor Unit',
      detail: 'processor unit created Successfully ',
      newProcessor,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = createProcessorForProduction;
