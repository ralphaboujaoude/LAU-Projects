const appendFormMessageToList = (message) => {
  const div = document.createElement("div");
  div.className = "message-container";
  div.innerHTML = `<p class="message-sender-fullname">
    ${message.name}
    <small class="message-sender-email"
      >&lt;${message.email}&gt;</small
    >
  </p>
  <p class="message-content">
    ${message.message}
  </p>`;
  messagesList.append(div);
};

const getFormMessages = async () => {
  const res = await axios({
    method: "get",
    url: "/api/form/",
  });
  console.log("THE RES:", res);
  const { data } = res;
  data.forEach((message) => {
    appendFormMessageToList(message);
  });
};

getFormMessages();
