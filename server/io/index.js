const { joinProcessorConnection } = require('./joinProcessorConnection')

const ioHandler = (io) => (socket) => {
  try {
    
    socket.broadcast.emit('msg', 'some one joined')
    socket.on('joinProcessorConnection', joinProcessorConnection(io,socket))

  } catch (error) {
    console.log(error, "here is the error");
  }

  socket.on('disconnect', () => {
    io.emit('msg', 'some one disconnected')
  })

};

module.exports = ioHandler;
