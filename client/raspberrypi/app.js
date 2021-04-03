const config = require("./config.js");
const socket = require("socket.io-client")(config.server_url, {
    transportOptions: {
        polling: {
            extraHeaders: {
                'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwcm9jZXNzb3JJZCI6IjIifQ.oFU_d3jc_2laGDvVWX82XCCu20QMUAAkU04APIEm0W8",
                // 'processor_id': '14'
            }
        }
    }
});


const processorId = 15
// socket.on("connection", () => {
//   socket.emit('user connected');
//   console.log("Connected to server");

//   socket.on("testRas", function(state){
//     console.log("The new state is: " + state);
//   });

//   socket.no("disconnect",()=>{
//     socket.emit('msge','bye')
//   })
// })

socket.emit('joinProcessorConnection', ({ processorId, }))

socket.on('msg', msg => {
    console.log(msg);
})

socket.on('statusChangeReport', obj => {
    console.log({ obj });
    // some function to hahndel the change of the status 
})

// an event to send the changes
// switch.addeventlistiner('change',e=>{
//   const data = {controllerId:e.target.id,
//   status:e.target.value}
// socket.emit('statusChange', data)
// })

// socket.on('statusChangeReport',obj=>{
//   console.log(obj);
// })
