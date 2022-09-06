let devices;
let shoppingItems = [];
let shoppingPrices = [];
var xmlhttp = new XMLHttpRequest(); // new HttpRequest instance
xmlhttp.onreadystatechange = function () {
  if (this.readyState == 4 && this.status == 200) {
    devices = JSON.parse(this.responseText);
    let devicesContainer = document.getElementsByClassName("cardssection")[0];
    devices.map((device, i) => {
      let deviceContent = document.createElement("div");
      deviceContent.classList.add("card");
      deviceContent.innerHTML = `<div class="cardcontent">
                            <img src="${device.img}">
                            <div class="item">${device.title}</div>
                            <div class="price">$${device.price}</div>
                            <div class="desc">${device.description}</div>
                            <button class="add-item" onclick="addToCart(${i})">add</button>
                        </div>`;
      devicesContainer.appendChild(deviceContent);
    });
  }
};
xmlhttp.open("GET", "/api/devices");
xmlhttp.send();

function addToCart(index) {
  if (shoppingItems.includes(index)) {
    alert("Item already added!");
    return;
  }
  let shoppingItem = document.createElement("div");
  let device = devices[index];
  shoppingItem.innerHTML = `<div class="image-box">
                    <img src="${device.img}" style="height:120px" />
                </div>
                <div class="about">
                    <h1 class="title">${device.title}</h1>
                </div>
                <div class="counter">
                    <button id="increase-btn-${index}" class="btn">+</button>
                    <div id="item-count-${index}" class="count">1</div>
                    <button id="decrease-btn-${index}" class="btn">-</button>
                </div>
                <div class="prices">
                    <div class="amount">\$${device.price}</div>
                    <button id="remove-btn-${index}" class="remove">Remove</button>
                </div>`;
  document.getElementsByClassName("Cart-Items")[0].appendChild(shoppingItem);
  shoppingItems.push(index);
  shoppingPrices.push(device.price);
  calculateTotalItems(shoppingItems);
  calculateTotalPrice(shoppingPrices);

  document.getElementById(`increase-btn-${index}`).onclick = () => {
    let itemCount = document.getElementById(`item-count-${index}`);
    itemCount.innerHTML = parseInt(itemCount.textContent) + 1;
    shoppingPrices.push(device.price);
    calculateTotalPrice(shoppingPrices);
  };

  document.getElementById(`decrease-btn-${index}`).onclick = () => {
    let itemCount = document.getElementById(`item-count-${index}`);

    if (parseInt(itemCount.textContent) <= 1) {
      itemCount.innerHTML = 1;
      return;
    }

    itemCount.innerHTML = parseInt(itemCount.textContent) - 1;
    let i = shoppingPrices.indexOf(device.price);
    if (i > -1) {
      shoppingPrices.splice(i, 1);
    }
    calculateTotalPrice(shoppingPrices);
  };

  document.getElementById(`remove-btn-${index}`).onclick = () => {
    let itemCount = document.getElementById(`item-count-${index}`);
    for (var j = 0; j < parseInt(itemCount.textContent); j++) {
      let i = shoppingPrices.indexOf(device.price);
      if (i > -1) {
        shoppingPrices.splice(i, 1);
      }
    }
    calculateTotalPrice(shoppingPrices);
    let i = shoppingItems.indexOf(index);
    if (i > -1) {
      shoppingItems.splice(i, 1);
    }
    calculateTotalItems(shoppingItems);
    document.getElementsByClassName("Cart-Items")[0].removeChild(shoppingItem);
  };
}

function calculateTotalPrice(shoppingPrices) {
  document.getElementById(
    "total-amount"
  ).innerHTML = `\$${shoppingPrices.reduce((a, b) => a + b, 0)}`;
}

function calculateTotalItems(shoppingItems) {
  document.getElementById(
    "total-items"
  ).innerHTML = `${shoppingItems.length} items`;
}

const modal = document.getElementById("modal");
const modalClose = document.getElementById("modal-close");

modal.style.display = "none";
const openModal = () => {
  modal.style.display = "";
};

modalClose.addEventListener("click", () => {
  console.log("I have clicked");
  modal.style.display = "none";
});

document.getElementById("clear-cart").onclick = () => {
  shoppingPrices = [];
  shoppingItems = [];
  document.getElementById("total-items").innerHTML = "0 items";
  document.getElementById("total-amount").innerHTML = "$0";
  while (document.getElementsByClassName("Cart-Items")[0].firstChild) {
    document
      .getElementsByClassName("Cart-Items")[0]
      .removeChild(document.getElementsByClassName("Cart-Items")[0].firstChild);
  }
};

document.getElementById("checkout-btn").onclick = function () {
  if (shoppingItems.length === 0) {
    alert("Shopping cart is empty!");
    return;
  }
  let checkoutDevices = [];
  shoppingItems.forEach((i) => {
    let count = parseInt(
      document.getElementById(`item-count-${i}`).textContent
    );
    devices[i].count = count;
    console.log(devices[i]);
    checkoutDevices.push(devices[i]);
  });
  let totalPrice = shoppingPrices.reduce((a, b) => a + b, 0);
  localStorage.setItem("checkout", JSON.stringify(checkoutDevices));
  localStorage.setItem("totalPrice", totalPrice);
  window.location.href = "/checkout";
};
