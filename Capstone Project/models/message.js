const mongoose = require("mongoose");

const Message = mongoose.model(
  "Message",
  new mongoose.Schema({
    name: String,
    email: String,
    message: String,
  })
);

exports.Message = Message;
