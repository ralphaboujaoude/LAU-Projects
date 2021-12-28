const express = require("express");
const http = require("http");
const bcrypt = require("bcrypt");
var bodyParser = require("body-parser");
const path = require("path");
const jwt = require("jsonwebtoken");
const { User, Device, Message } = require("./data");
const app = express();
const server = http.createServer(app);
const mongoose = require("mongoose");

const PrivateKey = "ok";

mongoose
  .connect("mongodb://localhost/smartdevices")
  .then(() => {
    console.log("Connected...");
  })
  .catch((err) => {
    console.log("Error Connecting to MongoDB...", err);
  });

app.set("view engine", "ejs");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, "views/pages")));



app.get("/", (req, res) => {
  res.render("pages/index");
});

app.get("/devices", (req, res) => {
  res.render("pages/devices");
});

app.get("/about-us", (req, res) => {
  res.render("pages/about-us");
});

app.get("/contact-us", (req, res) => {
  res.render("pages/contact-us");
});

app.get("/register", (req, res) => {
  res.render("pages/register");
});

app.get("/login", (req, res) => {
  res.render("pages/login");
});

app.get("/checkout", (req, res) => {
  res.render("pages/checkout");
})

app.post("/register", async (req, res) => {
  try {
    let foundUser = await User.findOne({ email: req.body.email });

    if (!foundUser) {
      let hashPassword = await bcrypt.hash(req.body.password, 10);
      let newUser = new User({
        username: req.body.username,
        email: req.body.email,
        password: hashPassword,
      });

      await newUser.save();

      res.redirect("/");
    } else {
      res.sendStatus(400);
    }
  } catch {
    res.send("Internal server error");
  }
});

app.post("/login", async (req, res) => {
  try {
      console.log("Login received....");
    let foundUser = await User.findOne({ email: req.body.email });
    if (foundUser) {
      let submittedPass = req.body.password;
      let storedPass = foundUser.password;

      const passwordMatch = await bcrypt.compare(submittedPass, storedPass);
      if (passwordMatch) {
        let username = foundUser.username;
        let token = jwt.sign({ username: username }, PrivateKey);
        console.log("Sending token...");
        res.status(200).json({ token: token });
      } else {
        res.sendStatus(400)
      }
    } 
  } catch {
    res.send("Internal server error");
  }
});

app.post("/form", async (req, res) => {
  try {
    console.log("Submitting form...");
    console.log(req.body);
    let newmessage = new Message({
      name: req.body.name,
      email: req.body.email,
      message: req.body.message,
    });
    await newmessage.save();
    res.sendStatus(200);
  } catch (err) {
    res.send("Internal Server Error");
  }
});

app.get("/api/devices", async (req, res) => {
    console.log("Getting Devices...");
  let devices = await Device.find();
  res.send(devices);
});

server.listen(3000, function () {
  console.log("server is running on port: 3000");
});


