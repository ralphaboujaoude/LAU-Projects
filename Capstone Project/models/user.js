const mongoose = require("mongoose");

const User = mongoose.model(
  "User",
  new mongoose.Schema({
    username: String,
    password: String,
    email: String,
    isAdmin: {
      type: Boolean,
      default: false,
    },
  })
);

exports.User = User;
