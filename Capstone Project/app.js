const express = require("express");
const http = require("http");
const path = require("path");
const { socketConnection } = require("./utils/socketio");

const app = express();
const server = http.createServer(app);

app.set("view engine", "ejs");
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, "views/pages")));

require("./startup/db")();
require("./startup/routes")(app);
socketConnection(server);

server.listen(3000, function () {
  console.log("Server is running on port: 3000");
});
