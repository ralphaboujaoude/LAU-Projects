const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const { User } = require("../models/user");

module.exports = () => {
  mongoose
    .connect("mongodb://localhost/smartdevices")
    .then(() => {
      console.log("Connected...");
      createAdmin();
    })
    .catch((err) => {
      console.log("Error Connecting to MongoDB...", err);
    });
};

const createAdmin = async () => {
  try {
    let admin = User.find({ username: "admin" });
    if (!admin) {
      const hashPassword = await bcrypt.hash("admin", 10);
      admin = new User({
        username: "admin",
        email: "admin@example.com",
        password: hashPassword,
        isAdmin: true,
      });
      await admin.save();
      console.log("Admin successfully created!");
    }
  } catch (err) {
    console.log(err.message);
  }
};
