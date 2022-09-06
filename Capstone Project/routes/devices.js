const express = require("express");
const router = express.Router();

const { Device } = require("../models/device");

//Get all devices
router.get("/", async (req, res) => {
  console.log("Getting Devices...");
  let devices = await Device.find();
  res.send(devices);
});

//Get device by id
router.get("/:id", async (req, res) => {
  try {
    const device = await Device.findById(req.params.id);
    if (!device) {
      return res.status(404).send("Device with given ID does not exist!");
    }
    res.status(200).send(device);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

//Create a new device
router.post("/", async (req, res) => {
  try {
    let device = new Device({
      title: req.body.title,
      price: req.body.price,
      description: req.body.description,
      img: req.body.img,
      categories: req.body.categories,
    });
    await device.save();
    res.status(200).send(device);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

//Update device by id
router.put("/:id", async (req, res) => {
  try {
    const device = await Device.findByIdAndUpdate(req.params.id, {
      title: req.body.title,
      price: req.body.price,
      description: req.body.description,
      img: req.body.img,
      categories: req.body.categories,
    });

    if (!device) {
      return res.status(404).send("Device with given ID does not exist!");
    }

    await device.save();
    res.status(200).send(device);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

//Delete by id
router.delete("/:id", async (req, res) => {
  try {
    const device = await Device.findByIdAndDelete(req.params.id);

    if (!device) {
      return res.status(404).send("Device with given ID does not exist!");
    }
    res.status(200).send(device);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

module.exports = router;
