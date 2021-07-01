const express = require('express');
const http = require("http");
const socketIo = require("socket.io");
const {join} = require('path');
const compression = require('compression');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
// const logger = require('morgan');

const {verify} = require('./utils/jwt');
const router = require('./routes');
const ioHandler = require('./io');
const {clientError, serverError} = require('./controllers/errorHandle');

const app = express();
const server = http.createServer(app);
const io = socketIo(server);
app.io = io;

app.set('port', process.env.PORT || 3000);
app.disable('x-powered-by');

app.use(bodyParser.json());
app.use(cookieParser());
app.use(compression());
// app.use(logger('dev'));

app.use('/api/v1', router);
app.use(express.static(join(__dirname, '..', 'dist')));
app.get('*', (req, res) => {
  res.sendFile(join(__dirname, '..', 'dist', 'index.html'));
});

io.use(async (socket, next) => {
  // const token = socket.request.headers.cookie.match(/(?<=token=)(.*?)(?=;)/)[0];
  const {token} = socket.request.headers;
  try {
    console.log('socke123t', token)
    const decoded = await verify(token);
    socket.decoded = decoded;
    next();
  } catch (err) {
    next(new Error('Authentication error'));
  }
}).on('connection', ioHandler(io));

app.use(clientError);
app.use(serverError);

module.exports = {app, server};
