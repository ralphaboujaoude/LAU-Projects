let shoppingCart = document.getElementById("shopping-cart");
shoppingCart.style.display = "none";
let shoppingButton = document.getElementById("shopping-button");
shoppingButton.addEventListener("click", () => {
  if (shoppingCart.style.display == "none") shoppingCart.style.display = "";
  else shoppingCart.style.display = "none";
});
