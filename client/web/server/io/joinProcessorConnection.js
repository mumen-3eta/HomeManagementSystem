const { statusChange } = require('./statusChange');

const {
  getProcessorByConnectionId,
  getControllerByControllerId,
} = require('../database/queries');

const joinProcessorConnection = (io,socket) => async({controllerId }) => {
  const { controllerId:controllerIdFromToken,userId } = socket.decoded;
try {
    console.log({controllerIdFromToken: controllerIdFromToken,userId});
    const {
      rows: [controllerConnected],
    } = await getControllerByControllerId({ controllerId });

    if (!controllerConnected) {
      throw new Error('this controller is not yours')
    }

    const {processor_id:processorId} = controllerConnected;

    const {
      rows: [processorConnectionData],
  } = await getProcessorByConnectionId({ processorId });

    if (!(controllerId==controllerIdFromToken||processorConnectionData.user_id==userId)) {
      throw new Error('unauthorized to this room')
    }
    
    socket.join(controllerId)

    socket.to(controllerId).emit('msg', 'a user has joined')

    socket.on('statusChange', statusChange(io,socket,controllerId))
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  joinProcessorConnection,
}
