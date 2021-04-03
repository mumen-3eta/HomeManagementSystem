const boom = require('@hapi/boom');

const {
  getProcessorByConnectionId,
  getControllerByControllerId,
  // getControllerStatus,
  changeControllerStatus,
} = require('../../../database/queries');

const connectControllerToProcessor = async (req, res, next) => {
  try {
    // dont forget to un comment the next line later---------------------------------------------------
    const { userId } = req;
    // const userId = 1;

    const { controllerId } = req.body;
    const {
      rows: [controllerConnected],
    } = await getControllerByControllerId({ controllerId });

    if (!controllerConnected) {
      return next(
        boom.unauthorized('this controller is not yours')
      );
    }

    const {processor_id:processorId,status} = controllerConnected;
    const {
        rows: [processorConnectionData],
    } = await getProcessorByConnectionId({ processorId });
    
    // eslint-disable-next-line
    if (processorConnectionData.user_id != userId) {
        return next(
            boom.conflict('this processor is already connected to another user')
            );
        }
        
        // **************************************************************************
    // const { rows: [{status}] } = await getControllerStatus({controllerId});
        
    const newStatus = !status

    const { rows: controllerStatus } = await changeControllerStatus({controllerId,newStatus});

    return res.status(201).json({
      title: 'changing controller status',
      detail: 'changing controller status Successfully ',
      controllerStatus,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = connectControllerToProcessor;
