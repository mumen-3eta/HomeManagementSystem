const config = require("./config.js");
const socket = require("socket.io-client")(config.server_url, {
    transportOptions: {
        polling: {
            extraHeaders: {
                'token': "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2In0.-KnUgRX8krKD1kmmVzEdqjsDy5pzPaAbbu1qusASMcw",
                // 'processor_id': '15'
            }
        }
    }
});

const data = { controllerId: 51 }
const processorId = 3
socket.emit('joinProcessorConnection', ({ processorId }))

socket.on('msg', msg => {
    console.log(msg);
})

socket.on('statusChangeReport', body => {
    console.log(body);
    // some function to hahndel the change of the status 
})

// an event to send the changes
// switch.addeventlistiner('change',e=>{
//   const data = {controllerId:e.target.id,
//   status:e.target.value}
socket.emit('statusChange', data)
// })

// socket.on('statusChangeReport',obj=>{
//   console.log(obj);
// })
