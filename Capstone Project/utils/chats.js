const { Chat } = require("../models/chat");

const sendMessage = async (sender, receiver, message) => {
  try {
    const chat = new Chat({
      users: [sender, receiver],
      sender: sender,
      receiver: receiver,
      message: message,
    });

    return await chat.save();
  } catch (e) {
    return { error: e.message };
  }
};

const getMessages = async (username) => {
  try {
    return await Chat.find({ users: username });
  } catch (e) {
    return { error: e.message };
  }
};

exports.sendMessage = sendMessage;
exports.getMessages = getMessages;
