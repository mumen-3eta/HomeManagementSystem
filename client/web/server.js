const express = require('express');
// const serveStatic = require('serve-static');
const path = require('path');
let history = require('connect-history-api-fallback');

const app = express();

const staticFileMiddleware = express.static(path.join(__dirname + '/dist'));
// app.use('/', serveStatic(path.join(__dirname, '/dist')));

app.use(staticFileMiddleware);

app.use(history({
    disableDotRule: true,
    verbose: true
}));

app.use(staticFileMiddleware);

app.get('/', function (req, res) {
    res.render(path.join(__dirname + '/dist/index.html'));
});

let server = app.listen(process.env.PORT || 8080, function () {
    let port = server.address().port;
    console.log("App now running on port", port);
});
// const port = process.env.PORT || 8080;
// app.listen(port);

// eslint-disable-next-line no-console
// console.log(`listening on port: ${port}`);
