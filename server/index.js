require('dotenv').config();

const app = require('./app');

const port = app.get('port');

/***
 *  socket io need update ( not work)
 * ***/
const Express = require("express")();
const server = require("http").createServer(Express);
const SocketIo = require("socket.io");

const { verify } = require('./utils/jwt');

const {
    // getProcessorByConnectionId,
    // getControllerByControllerId,
  //   getControllerStatus,
    changeControllerStatus,
  } = require('./database/queries');

const io = SocketIo(server)

io.on('connection',async(socket)=>{
    try {
        console.log("hello**********************************************");
    const {token} = socket.request.headers;
    // console.log(socket.request.headers);
    
    socket.on('joinConnection',({processorId})=>{
        socket.join(processorId)

        socket.emit('msg', processorId)
    
        socket.broadcast.to(processorId).emit('msg','a user has joined')

        socket.on('statusChange',async(obj)=>{
            try {
                const { userId , processorUnitId } = await verify(token)
                console.log(userId,processorUnitId);
                if (userId||processorUnitId) {
                    console.log(obj);
                    // const {controllerId,newStatus} = obj
                    const { rows: controllerStatus } = await changeControllerStatus(obj) 
                    console.log(controllerStatus,"data");
                    socket.broadcast.to(processorId).emit('statusChangeReport',obj)
                }
                
            } catch (error) {
                console.log(error,"this is a database error");
            }
        })
    })

    } catch (error) {
        console.log(error,"here is the error");
    }

    

    

    socket.on('disconnect',()=>{
        io.emit('msg','some one disconnected')
    })
})
// const SocketIo = require("socket.io")(Http, {
//     cors: {
//         origin: "http://localhost:8080",
//         methods: ["GET", "POST"]
//     }
// });

// SocketIo.on("connection", socket => {
//     socket.on("all_messages", data => {
//         SocketIo.emit("all_messages_server", data);
//         // socket.broadcast.emit("all_messages", data);
//     })
//     socket.on("all_Type_Controller", data => {
//         SocketIo.emit("all_Type_Controller_server", data);
//     })
//     socket.on("all_Location_Controller", data => {
//         SocketIo.emit("all_Location_Controller_server", data);
//     })
//     socket.on("user_profileData", data => {
//         SocketIo.emit("user_profileData_server", data);
//     })
//     socket.on("user_All_Processor", data => {
//         SocketIo.emit("user_All_Processor_server", data);
//     })
//     socket.on("user_TypeControllerData", data => {
//         SocketIo.emit("user_TypeControllerData_server", data);
//     })
//     socket.on("user_LocationControllerData", data => {
//         SocketIo.emit("user_LocationControllerData_server", data);
//     })
// });

server.listen(3001, () => {
    // eslint-disable-next-line no-console
    console.log(`socket.io is running http://localhost:3001`);
});
app.listen(port, () => {
    // eslint-disable-next-line no-console
    console.log(`server is running http://localhost:${port}/api/v1`);
});
