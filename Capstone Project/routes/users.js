const express = require("express");
const router = express.Router();

const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");

const { User } = require("../models/user");

router.get("/", async (req, res) => {
  try {
    const users = await User.find();
    res.status(200).send(users);
  } catch (err) {
    res.status(500).send(error.message);
  }
});

router.post("/", async (req, res) => {
  try {
    let foundUser = await User.findOne({ email: req.body.email });

    if (!foundUser) {
      let hashPassword = await bcrypt.hash(req.body.password, 10);
      let newUser = new User({
        username: req.body.username,
        email: req.body.email,
        password: hashPassword,
        isAdmin: req.body.isAdmin ?? false,
      });

      await newUser.save();

      res.status(200).send(newUser);
    } else {
      res.sendStatus(400);
    }
  } catch {
    res.status(500).send(error.message);
  }
});

router.put("/:id/", async (req, res) => {
  try {
    let user = await User.findOne({ _id: req.params.id });

    if (user) {
      user.username = req.body.username;
      user.email = req.body.email;
      user.isAdmin = req.body.isAdmin;

      await user.save();

      res.status(200).send(user);
    } else {
      res.sendStatus(400);
    }
  } catch (err) {
    res.status(500).send(err.message);
  }
});

router.delete("/:id", async (req, res) => {
  try {
    const users = await User.findByIdAndDelete(req.params.id);
    res.status(200).send(users);
  } catch (err) {
    res.status(500).send(error.message);
  }
});

module.exports = router;
