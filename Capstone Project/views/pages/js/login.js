document.getElementById("log-error").style.display = "none";
let once = true;
let loginForm = document.getElementById("login-form");
loginForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const xmlhttp = new XMLHttpRequest();
  xmlhttp.open("POST", "/api/auth");
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.send(
    JSON.stringify({
      email: loginForm.elements["email"].value,
      password: loginForm.elements["password"].value,
    })
  );
  xmlhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let token = JSON.parse(this.response).token;
      localStorage.setItem("token", token);
      window.location.href = "/";
    }
    if (this.status == 400) {
      document.getElementById("log-error").style.display = "";
    }
  };
});
