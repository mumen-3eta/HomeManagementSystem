/* eslint-disable no-console */
const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const mongoose = require('mongoose');
const logger = require('morgan');

const { getSecret } = require('./secrets');
const router = require('./routes');

mongoose
  .connect(getSecret('dbUri'), {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    useCreateIndex: true,
  })
  .then(() => console.log('Connected to mongoDB'))
  .catch((err) => console.log('Error connecting to mongoDB', err));

const app = express();
const port = process.env.PORT || 3000;

app.use(bodyParser.json());
app.use(cookieParser());
app.use(logger('dev'));

app.use('/api/v1', router);

app.listen(port, () => {
  console.log(`Server running on port http://localhost:${port}`);
});

module.exports = { app };
