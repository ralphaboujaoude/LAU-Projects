const appendUserToList = (user) => {
  const tr = document.createElement("tr");
  tr.innerHTML = `<tr>
    <form id="form-${user._id}" onsubmit="updateUser(event)">
      <td hidden><input name="userId" value="${user._id}" form="form-${
    user._id
  }" disabled /></td>
      <td>
        <input
        class="option-input"
        type="email"
        name="email"
        value="${user.email}"
        form="form-${user._id}"
        />
      </td>
      <td> 
        <input
        class="option-input"
        type="text"
        name="username"
        value="${user.username}"
        form="form-${user._id}"
        />
      </td>
      <td>
        <input type="checkbox" name="isAdmin" value="yes" ${
          user.isAdmin ? "checked" : ""
        }  form="form-${user._id}" />
      </td>
      <td><button type="submit" class="option-btn-save" onclick="doSomething()" form="form-${
        user._id
      }" >Save</button></td>
      <td><button type="button" class="option-btn-delete" onclick="deleteUser('${
        user._id
      }')" form="form-${user._id}" >X</button></td>
    </form>
  </tr>`;

  usersList.insertBefore(tr, usersList.children[1]);
};

const getUsers = async () => {
  const res = await axios({
    method: "get",
    url: "/api/users/",
  });
  console.log("THE RES:", res);
  const { data } = res;
  data.forEach((user) => {
    appendUserToList(user);
  });
};

const createUser = async (event) => {
  event.preventDefault();
  console.log("I will create a new user");

  const data = {
    username: event.target.username.value,
    email: event.target.email.value,
    password: event.target.password.value,
    isAdmin: event.target.isAdmin.checked,
  };

  const res = await axios({
    method: "post",
    url: "/api/users/",
    data: data,
  });

  appendUserToList(res.data);
  event.res;
};

const updateUser = async (event) => {
  event.preventDefault();

  const data = {
    username: event.target.username.value,
    email: event.target.email.value,
    isAdmin: event.target.isAdmin.checked,
  };

  const res = await axios({
    method: "put",
    url: `/api/users/${event.target.userId.value}/`,
    data: data,
  });

  console.log(res);
};

const deleteUser = async (id) => {
  const res = await axios({
    method: "delete",
    url: `/api/users/${id}/`,
  });
  console.log(res);
  if (res.status == 200) {
    const form = document.getElementById(`form-${id}`);
    form.parentElement.remove(form.parentElement);
  }
};

getUsers();
