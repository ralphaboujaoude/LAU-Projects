let devices = [];
const chosen = [];
const recommendContainer = document.getElementById("recommend-container");
const recommendResult = document.getElementById("recommend-result");

const choice122 = [
  { choice: "Portable", next: null },
  { choice: "Stationary", next: null },
];
const choice121 = [
  { choice: "Budget", next: null },
  { choice: "Performance", next: null },
];
const choice12 = [
  { choice: "Gaming", next: choice121 },
  { choice: "Business", next: choice122 },
  { choice: "Programming", next: choice121 },
];

//Phone Choices
const choice111 = [
  { choice: "Android", next: null },
  { choice: "IOS", next: null },
];
const choice11 = [
  { choice: "Budget", next: choice111 },
  { choice: "Performance", next: choice111 },
];

//Main Choices
const choice1 = [
  { choice: "Phone", next: choice11 },
  { choice: "Laptop", next: choice12 },
];

const displayDevice = (device) => {
  const div = document.createElement("div");
  div.className = "card";
  div.innerHTML = `<div class="cardcontent">
  <img src="${device.img}">
  <div class="item">${device.title}</div>
  <div class="price">$${device.price}</div>
  <div class="desc">${device.description}</div>
  <button class="button"><a href="/devices">Check out the device</a></button></div>`;
  recommendResult.appendChild(div);
};

const getResult = () => {
  let maxCount = 0;
  for (let i = 0; i < devices.length; i++) {
    devices[i].count = 0;
  }

  chosen.forEach((choice) => {
    for (let i = 0; i < devices.length; i++) {
      if (devices[i].categories.includes(choice)) {
        if (choice == "IOS" || choice == "Android") {
          console.log("I AM HERE");
          devices[i].count += 5;
        }
        devices[i].count++;
      }
      if (maxCount < devices[i].count) maxCount = devices[i].count;
    }
  });

  const chosenDevices = [];
  for (let i = 0; i < devices.length; i++) {
    if (devices[i].count == maxCount) chosenDevices.push(devices[i]);
  }

  chosenDevices.forEach((device) => {
    displayDevice(device);
  });
  console.log("CHOSEN DEVICES:", chosenDevices);
  recommendResult.innerHTML += `<br /><br /><div><button class="button"><a href="/recommend">Recommend Again</a></button></div>`;
};

const decide = (choice) => {
  chosen.push(choice.choice);
  if (choice.next == null) {
    console.log(chosen);
    displayChoice([]);
    getResult(chosen);
    return;
  }
  displayChoice(choice.next);
};

const displayChoice = (choice) => {
  recommendContainer.innerHTML = "";
  if (choice == null) {
    return;
  }
  choice.forEach((c) => {
    const div = document.createElement("div");
    div.className = "recommend";
    div.addEventListener("click", () => {
      decide(c);
    });
    div.textContent = c.choice;
    recommendContainer.appendChild(div);
    // recommendContainer.innerHTML += `<div class="recommend" onclick="decide(${c.next})">${c.choice}</div>`;
  });
};

const getDevices = async () => {
  const res = await axios({
    method: "get",
    url: "/api/devices/",
  });
  const { data } = res;
  devices = data;
  console.log(devices);
};

getDevices();
displayChoice(choice1);

const addChoice = () => {};
