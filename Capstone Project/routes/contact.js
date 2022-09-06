const express = require("express");
const router = express.Router();

const { Message } = require("../models/message");

router.post("/", async (req, res) => {
  try {
    console.log("Submitting form...");
    console.log(req.body);
    let newMessage = new Message({
      name: req.body.name,
      email: req.body.email,
      message: req.body.message,
    });
    await newMessage.save();
    res.sendStatus(200);
  } catch (err) {
    res.send("Internal Server Error");
  }
});

router.get("/", async (req, res) => {
  try {
    const messages = await Message.find();
    res.status(200).send(messages);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

module.exports = router;
