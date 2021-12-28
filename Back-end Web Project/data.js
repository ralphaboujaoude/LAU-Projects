const mongoose = require("mongoose");

const User = mongoose.model("User", new mongoose.Schema({
    username: String,
    password: String,
    email: String,
}));

const Device = mongoose.model("Device", new mongoose.Schema({
    title: String,
    price: Number,
    description: String,
    img: String,
}));

const Message = mongoose.model("Message", new mongoose.Schema({
    name: String,
    email: String,
    message: String,
}));

exports.User = User;
exports.Device = Device;
exports.Message = Message;