const usersList = document.getElementById("users-list");
const usersTitle = document.getElementById("users-title");

const devicesList = document.getElementById("devices-list");
const devicesTitle = document.getElementById("devices-title");

const messagesList = document.getElementById("messages-list");
const messagesTitle = document.getElementById("messages-title");

window.onload = () => {
  usersList.style.display = "none";
  devicesList.style.display = "none";
  messagesList.style.display = "none";
};

usersTitle.addEventListener("click", () => {
  usersList.style.display = usersList.style.display == "none" ? "" : "none";
});

devicesTitle.addEventListener("click", () => {
  devicesList.style.display = devicesList.style.display == "none" ? "" : "none";
});

messagesTitle.addEventListener("click", () => {
  messagesList.style.display =
    messagesList.style.display == "none" ? "" : "none";
});
