const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
const router = express.Router();

const { User } = require("../models/user");

const PrivateKey = "ok";

router.post("/", async (req, res) => {
  try {
    console.log("Login received....");
    let foundUser = await User.findOne({ email: req.body.email });
    if (foundUser) {
      let submittedPass = req.body.password;
      let storedPass = foundUser.password;

      const passwordMatch = await bcrypt.compare(submittedPass, storedPass);
      if (passwordMatch) {
        let username = foundUser.username;
        let token = jwt.sign({ username: username, isAdmin: foundUser.isAdmin }, PrivateKey);
        console.log("Sending token...");
        res.status(200).json({ token: token });
      } else {
        res.sendStatus(400);
      }
    }
  } catch {
    res.send("Internal server error");
  }
});

module.exports = router;
