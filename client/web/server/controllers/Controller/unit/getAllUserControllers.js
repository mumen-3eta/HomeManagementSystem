const boom = require('@hapi/boom');

const { getControllerByUserId } = require('../../../database/queries');

const getAllProcessorControllers = async (req, res, next) => {
  try {
    const { userId } = req;

    const { rows: connectionData } = await getControllerByUserId({
      userId,
    });

    if (!connectionData.length) {
      connectionData.push('no controllers connected yet');
    }

    return res.status(201).json({
      title: 'fetch all controllers for this user',
      detail: 'user controllers data collected Successfully ',
      connectionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllProcessorControllers;
