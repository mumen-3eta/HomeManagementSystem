let config = {}

config.led = 7;

// config.server_url = "https://hmsy-app2.herokuapp.com";
config.server_url = "http://localhost:3000";

if (config.led == -1 || config.server_url == "") {
  console.log("Before running app.js you must configure the LED pin number and server url in ./config.js");
  process.exit(-1);
}

module.exports = config;
