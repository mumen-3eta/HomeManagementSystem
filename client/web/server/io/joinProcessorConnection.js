const {statusChange} = require('./statusChange');

const {
  getProcessorByConnectionId,
  getControllerByControllerId,
  changeControllerStatus,
} = require('../database/queries');

const joinProcessorConnection = (io, socket) => async ({controllerId}) => {
  const {controllerId: controllerIdFromToken, userId} = socket.decoded;
  try {
    const {
      rows: [controllerConnected],
    } = await getControllerByControllerId({controllerId});

    if (!controllerConnected) {
      throw new Error('this controller is not yours')
    }

    const {processor_id: processorId} = controllerConnected;

    const {
      rows: [processorConnectionData],
    } = await getProcessorByConnectionId({processorId});

    if (!(controllerId == controllerIdFromToken || processorConnectionData.user_id == userId)) {
      throw new Error('unauthorized to this room')
    }

    socket.join(controllerId)

    socket.to(controllerId).emit('msg', 'a user has joined')
    //----------
    const {
      rows: [{controller_id, status}],
    } = await getControllerByControllerId({controllerId});

    //-----------------------
    io.to(controllerId).emit('statusChangeReport', [{controller_id, status}])
    socket.on('statusChange', statusChange(io, socket, controllerId))
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  joinProcessorConnection,
}
