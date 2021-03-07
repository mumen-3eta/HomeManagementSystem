const boom = require('@hapi/boom');

const { getProcessorConnectionByUserId } = require('../../database/queries');

const getProcessorConnection = async (req, res, next) => {
  try {
    // dont forget to un comment the next line later---------------------------------------------------
    // const { userId } = req;
    const userId = 1;

    const { rows: connectionData } = await getProcessorConnectionByUserId({
      userId,
    });

    if (!connectionData[0]) {
      return next(boom.conflict('you dont have any connected processor units'));
    }

    return res.status(201).json({
      title: 'processor connection data',
      detail: 'processor unit connection data collected Successfully ',
      connectionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = getProcessorConnection;
