let logout = document.getElementById("logout");
if (localStorage.getItem("token")) logout.style.display = "";
else logout.style.display = "none";
logout.onclick = function () {
  localStorage.removeItem("token");
  window.location.href = "/";
};

function parseJwt(token) {
  var base64Url = token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
}

window.onload = (event) => {
  if (localStorage.getItem("token") == null) return;
  let token = localStorage.getItem("token");
  if (token !== null) {
    let loginSignUp = document.getElementsByClassName("not-logged");
    let userDisplay = document.getElementsByClassName("logged")[0];
    let username = parseJwt(token).username;

    loginSignUp[0].style.display = "none";
    loginSignUp[1].style.display = "none";

    userDisplay.innerHTML = `<a>HELLO, ${username}</a>`;
  } else {
    let userDisplay = document.getElementsByClassName("logged")[0];
    username.style.display = "none";
  }
};
