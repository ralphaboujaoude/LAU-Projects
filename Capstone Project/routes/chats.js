const express = require("express");
const router = express.Router();

const { Chat } = require("../models/chat");

router.get("/:username", async (req, res) => {
  try {
    const messages = await Chat.find({ users: req.params.username });
    res.status(200).send(messages);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

router.post("/:username", async (req, res) => {
  try {
    const chat = new Chat({
      users: ["admin", req.params.username],
      sender: req.body.sender,
      receiver: req.body.receiver,
      message: req.body.message,
    });

    await chat.save();
  } catch (err) {}
});

module.exports = router;
