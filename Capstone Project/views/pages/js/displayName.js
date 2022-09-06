const logout = document.getElementById("logout");
const AdminPanel = document.getElementById("admin-panel");
AdminPanel.style.display = 'none';

function parseJwt(token) {
  if (!token) return undefined;
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
  if (localStorage.getItem("token")) {
    logout.style.display = "";
  } else {
    logout.style.display = "none";
  }
  logout.onclick = function () {
    localStorage.removeItem("token");
    window.location.href = "/";
  };

  if (localStorage.getItem("token") == null) return;
  let token = localStorage.getItem("token");
  if (token !== null) {
    let loginSignUp = document.getElementsByClassName("not-logged");
    let userDisplay = document.getElementsByClassName("logged")[0];
    let {username, isAdmin} = parseJwt(token);

    loginSignUp[0].style.display = "none";
    loginSignUp[1].style.display = "none";

    
    console.log("IS ADMIN:",isAdmin)
    if(isAdmin)
      AdminPanel.style.display = "";

    userDisplay.innerHTML = `<a>HELLO, ${username}</a>`;
  } else {
    let userDisplay = document.getElementsByClassName("logged")[0];
    username.style.display = "none";
  }
};
