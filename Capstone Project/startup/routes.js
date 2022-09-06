const users = require("../routes/users");
const auth = require("../routes/auth");
const contact = require("../routes/contact");
const devices = require("../routes/devices");

module.exports = (app) => {
  app.get("/", (req, res) => {
    res.render("pages/index");
  });

  app.get("/devices", (req, res) => {
    res.render("pages/devices");
  });

  app.get("/about-us", (req, res) => {
    res.render("pages/about-us");
  });

  app.get("/contact-us", (req, res) => {
    res.render("pages/contact-us");
  });

  app.get("/recommend", (req, res) => {
    res.render("pages/recommend");
  });

  app.get("/register", (req, res) => {
    res.render("pages/register");
  });

  app.get("/login", (req, res) => {
    res.render("pages/login");
  });

  app.get("/checkout", (req, res) => {
    res.render("pages/checkout");
  });

  app.get("/admin", (req, res) => {
    res.render("pages/admin");
  });

  app.use("/api/users", users);
  app.use("/api/auth", auth);
  app.use("/api/form", contact);
  app.use("/api/devices", devices);
};
