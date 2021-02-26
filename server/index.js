const app = require('./app');

const port = app.get('port');

/***
 *  socket io need update ( not work)
 * ***/
const Express = require("express")();
const Http = require("http").Server(Express);
const SocketIo = require("socket.io")(Http, {
    cors: {
        origin: "http://localhost:8080",
        methods: ["GET", "POST"]
    }
});
// //test
let position = {x: 250, y: 250,};
SocketIo.on("connection", socket => {
    console.log(socket);
    socket.emit("position", position);
    socket.on("move", data => {
        switch (data) {
            case "left" :
                position.x -= 5;
                SocketIo.emit("position", position);
                break;
            case "right" :
                position.x += 5;
                SocketIo.emit("position", position);
                break;
            case "up" :
                position.y -= 5;
                SocketIo.emit("position", position);
                break;
            case "down" :
                position.y += 5;
                SocketIo.emit("position", position);
                break;
        }
    })
    socket.on("all_messages", data => {
        SocketIo.emit("all_messages", data);
        // socket.broadcast.emit("all_messages", data);
    })
});

Http.listen(3001, () => {
    // eslint-disable-next-line no-console
    console.log(`server is running http://localhost:3001`);
});
app.listen(port, () => {
    // eslint-disable-next-line no-console
    console.log(`server is running http://localhost:${port}/api/v1`);
});
