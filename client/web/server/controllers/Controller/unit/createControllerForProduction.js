const boom = require('@hapi/boom');

const { createControllerProduction } = require('../../../database/queries');

const createControllerForProduction = async (req, res, next) => {
  try {
    const { rows: newController } = await createControllerProduction();

    return res.status(201).json({
      title: 'creating Controller Unit',
      detail: 'Controller unit created Successfully ',
      newController,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = createControllerForProduction;
