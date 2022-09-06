const { sendMessage, getMessages } = require("./chats");

let io;

exports.socketConnection = (server) => {
  io = require("socket.io")(server);

  console.log("Initiating server socket...");

  io.on("connection", (socket) => {
    console.info(`Client connected [id=${socket.id}]`);
    socket.join(socket.request._query.id);

    socket.on("identify", async (socket) => {
      console.log("Socket:", socket.username);
      const messages = await getMessages(socket.username);
      io.emit("messages", { messages });
    });

    socket.on("send-message", async (socket) => {
      console.log(`From "${socket.username}": ${socket.message}`);

      const message = await sendMessage(
        socket.sender,
        socket.receiver,
        socket.message
      );

      io.emit("new-message", { message });
    });

    socket.on("disconnect", () => {
      console.info(`Client disconnected [id=${socket.id}]`);
    });
  });
};
