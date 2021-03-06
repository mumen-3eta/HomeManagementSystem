require('dotenv').config();

const express = require('express');
const compression = require('compression');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const logger = require('morgan');

const router = require('./routes');
const { clientError, serverError } = require('./controllers/errorHandle');

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
