const { joinProcessorConnection } = require('./joinProcessorConnection')

const ioHandler = (io) => (socket) => {
  try {
    console.log("hello ->**********************************************");
    // 
    // console.log(socket.request.headers);
    // console.log("hi");
    socket.on('joinProcessorConnection', joinProcessorConnection(socket))

  } catch (error) {
    console.log(error, "here is the error");
  }

  socket.on('disconnect', () => {
    io.emit('msg', 'some one disconnected')
  })

};

module.exports = ioHandler;
