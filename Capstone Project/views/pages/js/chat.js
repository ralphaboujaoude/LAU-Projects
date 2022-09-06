const chatBox = document.getElementById("chatbox");
const title = document.getElementById("chatbox-title");
const chatBody = document.getElementById("chat-body");
const chatForm = document.getElementById("chat-form");
const Messages = document.getElementById("chat-messages");
const messageInput = document.getElementById("chat-input");

const token = localStorage.getItem("token");
const username =
  parseJwt(token) !== undefined ? parseJwt(token).username : undefined;
const timeToWait = 1000;

if (token) {
  chatBox.style.display = "";
} else {
  chatBox.style.display = "none";
}
chatBody.style.display = "none";

function toggleChatBox() {
  if (chatBody.style.display == "none") {
    chatBody.style.display = "";
  } else {
    chatBody.style.display = "none";
  }
}

const displayNewMessage = (message) => {
  const li = document.createElement("li");
  li.innerText = message.message;
  li.className = `chat-message chat-message-${
    message.sender == username ? "user" : "admin"
  }`;
  Messages.append(li);
};

const displayMessages = (messages) => {
  messages.forEach((message) => {
    displayNewMessage(message);
  });
};
