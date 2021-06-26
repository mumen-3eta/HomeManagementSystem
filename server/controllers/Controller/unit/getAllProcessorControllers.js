const boom = require('@hapi/boom');

const {
  getProcessorByConnectionId,
  getControllerByProcessorId,
} = require('../../../database/queries');

const getAllProcessorControllers = async (req, res, next) => {
  try {
    const { userId } = req;
    const { processorId } = req.body;

    const { rows: processorData } = await getProcessorByConnectionId({
      processorId,
    });

    if (!processorData.length || processorData[0].user_id !== userId) {
      return next(boom.conflict('processor unit dont belong to you'));
    }

    const { rows: connectionData } = await getControllerByProcessorId({
      processorId,
    });

    if (!connectionData.length) {
      connectionData.push('no controllers connected yet');
    }

    return res.status(201).json({
      title: 'fetch all controllers for this processor',
      detail: 'processor unit controllers data collected Successfully ',
      connectionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getAllProcessorControllers;
