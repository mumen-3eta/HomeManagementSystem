const boom = require('@hapi/boom');

const { deleteProcessorConnectionData } = require('../../database/queries');

const deleteProcessorConnection = async (req, res, next) => {
  try {
    const { userId } = req;

    const { processorId } = req.body;

    await deleteProcessorConnectionData({
      userId,
      processorId,
    });

    return res.status(201).json({
      title: 'delete processor connection',
      detail: 'processor unit connection data deleted Successfully ',
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = deleteProcessorConnection;
