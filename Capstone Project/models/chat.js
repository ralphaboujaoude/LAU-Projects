const mongoose = require("mongoose");

const Chat = mongoose.model(
  "Chat",
  new mongoose.Schema({
    users: [{ type: String }],
    sender: String,
    receiver: String,
    message: String,
  })
);

exports.Chat = Chat;
