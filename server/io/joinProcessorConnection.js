const { statusChange } = require('./statusChange');

const joinProcessorConnection = (socket) => ({processorId }) => {
//  const processorId = processorId ? processorId : { processorId } = socket.decoded;
  // const { processorId } = socket.decoded;
  socket.join(processorId)

  socket.emit('msg', processorId)

  socket.broadcast.to(processorId).emit('msg', 'a user has joined')

  socket.on('statusChange', statusChange(socket))
}

module.exports = {
  joinProcessorConnection,
}
