const mongoose = require("mongoose");

const Device = mongoose.model(
  "Device",
  new mongoose.Schema({
    title: String,
    price: Number,
    description: String,
    img: String,
    categories: [String],
  })
);

exports.Device = Device;
