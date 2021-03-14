const boom = require('@hapi/boom');

const {
  connectControllerProcessor,
  getProcessorConnectionByUserId,
  getControllerByControllerId,
  getControllerType,
  getControllerLocation,
} = require('../../../database/queries');

const connectControllerToProcessor = async (req, res, next) => {
  try {
    // dont forget to un comment the next line later---------------------------------------------------
    const { userId } = req;
    // const userId = 1;

    const { processorId, typeId, locationId, controllerId } = req.body;
    const {
      rows: [controllerConnected],
    } = await getControllerByControllerId({ controllerId });

    if (controllerConnected) {
      return next(
        boom.conflict('this controller is already connected to another user')
      );
    }

    const {
      rows: [processorConnectionData],
    } = await getProcessorConnectionByUserId({ userId });

    // eslint-disable-next-line
    if (processorConnectionData.id != processorId) {
      return next(
        boom.conflict('this processor is already connected to another user')
      );
    }

    const {
      rows: [controllerType],
    } = await getControllerType({ typeId });

    if (!controllerType) {
      return next(boom.conflict('type not existing '));
    }

    const {
      rows: [controllerLocation],
    } = await getControllerLocation({ locationId });

    if (!controllerLocation) {
      return next(boom.conflict('location not existing '));
    }

    const { rows: connectionData } = await connectControllerProcessor(req.body);

    return res.status(201).json({
      title: 'connecting processor Unit',
      detail: 'processor unit connected Successfully ',
      connectionData,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = connectControllerToProcessor;
