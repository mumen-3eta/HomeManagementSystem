const {
  getProcessorByConnectionId,
  getControllerByControllerId,
  changeControllerStatus,
} = require('../database/queries');

module.exports.statusChange = (socket) => async (obj) => {
  try {
    const { processorId } = socket.decoded;

    // const { verify } = require('./utils/jwt'); /////
    // const { userId, processorUnitId } = await verify(token)
    const { userId } = socket.decoded;
    // const {controllerId} = obj;
    console.log({ userId })
    // if (userId || processorUnitId) {
    console.log({ obj });
    // const {controllerId,newStatus} = obj
    // const { rows: controllerStatus } = await changeControllerStatus(obj)

    // }

    const { controllerId } = obj;
    const {
      rows: [controllerConnected],
    } = await getControllerByControllerId({ controllerId });

    if (!controllerConnected) {
      throw new Error('this controller is not yours')
    }

    const { status } = controllerConnected;
    const {
      rows: [processorConnectionData],
    } = await getProcessorByConnectionId({ processorId });

    // eslint-disable-next-line
    if (processorConnectionData.user_id != userId) {
      throw new Error('this processor is already connected to another user')
    }

    // **************************************************************************
    // const { rows: [{status}] } = await getControllerStatus({controllerId});

    const newStatus = !status

    const { rows: controllerStatus } = await changeControllerStatus({ controllerId, newStatus });

    console.log(controllerStatus, "data");
    socket.broadcast.to(processorId).emit('statusChangeReport', obj)
    // return res.status(201).json({
    //   title: 'changing controller status',
    //   detail: 'changing controller status Successfully ',
    //   controllerStatus,
    // })

  } catch (error) {
    console.log(error);
  }
}