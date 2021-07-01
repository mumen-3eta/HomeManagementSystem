const config = require("./config.js");
var gpiop = require('rpi-gpio').promise;
const socket = require("socket.io-client")(config.server_url,{
  transportOptions: {
    polling: {
      extraHeaders: {
        // 'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjI1LCJpc0FkbWluIjpmYWxzZSwiaWF0IjoxNjE3NDQ4MjY0fQ.mv71321xRTfgumlLZNestPsxP3LaTJC9G4B4vkzwicA",
        // 'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb250cm9sbGVySWQiOiIzIn0.8sA3-R1LohBuc4pgkC06C8LQrbPF2leiqC0ZVbbf1Gk",
        // 'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb250cm9sbGVySWQiOiIxNSJ9.mRx5pJEJ8XWfzVWs0J6u7W5jKn5Y67tkjb5TyTXJp8k",
        // 'processor_id': '15'
        'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb250cm9sbGVySWQiOjF9.J724y_9YvP1hRkyPrpTk_3lRs7aw-qFQmYnrvAUXfwQ",
      }
    }
  }
}); 
const controllerId = 1

process.on('SIGINT', function () {
  gpiop
    .write(config.led, false)
    .then(() => {
      return gpiop.destroy();
    })
    .then(() => {
      process.exit();
    });
});

gpiop
  .setup(config.led, gpiop.DIR_OUT)
  .then(() => {
    return gpiop.write(config.led, false);
  })
  .catch((err) => {
    console.log('Error: ', err.toString());
  });

console.log('hi');
socket.emit("connection", () => {
  socket.emit('user connected');
  console.log("Connected to server");

//   socket.on("testRas", function(state){
//     console.log("The new state is: " + state);
//   });

//   socket.no("disconnect",()=>{
//     socket.emit('msge','bye')
//   })
})

socket.emit('joinProcessorConnection',({controllerId}))

socket.on('msg',msg=>{
  console.log(msg);
})

socket.on('statusChangeReport',body=>{
  console.log('The new state is: ' + body);
  gpiop.write(config.led, !body[0].status);
  // some function to hahndel the change of the status 
})

// an event to send the changes
// switch.addeventlistiner('change',e=>{
//   const data = {controllerId:e.target.id,
//   status:e.target.value}
  // socket.emit('statusChange',data)
// })

// socket.on('statusChangeReport',obj=>{
//   console.log(obj);
// })



