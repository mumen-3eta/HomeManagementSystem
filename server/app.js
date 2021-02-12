require('dotenv').config();

const express = require('express');
const compression = require('compression');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const mongoose = require('mongoose');
const logger = require('morgan');

const { getSecret } = require('./secrets');
const router = require('./routes');
const { clientError, serverError } = require('./controllers/errorHandle');

mongoose
  .connect(getSecret('dbUri'), {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    useCreateIndex: true,
  })
  .then(() => console.log('Connected to mongoDB'))
  .catch((err) => console.log('Error connecting to mongoDB', err));

const app = express();
app.set('port', process.env.PORT || 3000);
app.disable('x-powered-by');

app.use(bodyParser.json());
app.use(cookieParser());
app.use(compression());
app.use(logger('dev'));

app.use('/api/v1', router);

app.use(clientError);
app.use(serverError);

module.exports = app;
