const socket = io();

socket.on("connect", () => {
  socket.emit("identify", { username });
});

socket.on("messages", (server) => {
  displayMessages(server.messages);
});

socket.on("new-message", (server) => {
  displayNewMessage(server.message);
});

const sendMessage = (e) => {
  e.preventDefault();
  socket.emit("send-message", {
    message: e.target.message.value,
    username,
    sender: username,
    receiver: "admin",
  });
  e.target.message.value = "";
};
