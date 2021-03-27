const boom = require('@hapi/boom');

const {
  connectProcessorUser,
  getProcessorByProcessorId,
} = require('../../database/queries');

const createProcessorForProduction = async (req, res, next) => {
  try {
    const { userId } = req;

    const { processorId } = req.body;
    const {
      rows: [ProcessorConnected],
    } = await getProcessorByProcessorId({ processorId });

    if (ProcessorConnected) {
      return next(
        boom.conflict('this processor is already connected to a user')
      );
    }

    const { rows: connectionData } = await connectProcessorUser({
      userId,
      processorId,
    });

    return res.status(201).json({
      title: 'connecting processor Unit',
      detail: 'processor unit connected Successfully ',
      connectionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = createProcessorForProduction;
