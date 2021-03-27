const boom = require('@hapi/boom');

const {
  deleteControllerConnection,
  getProcessorByConnectionId,
} = require('../../../database/queries');

const deleteProcessorConnection = async (req, res, next) => {
  try {
    const { userId } = req;

    const { processorId, controllerId } = req.body;

    const { rows: processorData } = await getProcessorByConnectionId({
      processorId,
    });

    if (!processorData.length || processorData[0].user_id !== userId) {
      return next(boom.conflict('processor unit dont belong to you'));
    }

    await deleteControllerConnection({ controllerId, processorId });

    return res.status(201).json({
      title: 'delete controller connection',
      detail: 'controller unit connection data deleted Successfully ',
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = deleteProcessorConnection;
