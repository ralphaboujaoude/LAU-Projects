const appendDeviceToList = (device) => {
  const tr = document.createElement("tr");
  tr.innerHTML = `<tr>
      <form id="form-${device._id}" onsubmit="updateDevice(event)">
        <td hidden><input name="deviceId" value="${device._id}" form="form-${
    device._id
  }" disabled /></td>
    <td>
    <input
      class="option-new-input"
      type="text"
      name="title"
      placeholder="Title"
      value="${device.title}"
      form="form-${device._id}"
      required
    />
  </td>
  <td>
    <input
      class="option-new-input"
      type="number"
      name="price"
      placeholder="Price"
      value=${device.price}
      form="form-${device._id}"
      required
    />
  </td>
  <td>
    <textarea
      class="option-new-input"
      name="description"
      placeholder="Description"
      form="form-${device._id}"
      required
    >${device.description}</textarea>
  </td>
  <td>
    <select name="categories" multiple form="form-${device._id}" required>
      <option value="Phone" ${
        device.categories.includes("Phone") ? "selected" : ""
      }>Phone</option>
      <option value="Laptop" ${
        device.categories.includes("Laptop") ? "selected" : ""
      }>Laptop</option>
      <option value="IOS" ${
        device.categories.includes("IOS") ? "selected" : ""
      }>IOS</option>
      <option value="Android" ${
        device.categories.includes("Android") ? "selected" : ""
      }>Android</option>
      <option value="Budget" ${
        device.categories.includes("Budget") ? "selected" : ""
      }>Budget</option>
      <option value="Performance" ${
        device.categories.includes("Performance") ? "selected" : ""
      }>Performance</option>
      <option value="Portable" ${
        device.categories.includes("Portable") ? "selected" : ""
      }>Portable</option>
      <option value="Gaming" ${
        device.categories.includes("Portable") ? "selected" : ""
      }>Gaming</option>
      <option value="Business" ${
        device.categories.includes("Business") ? "selected" : ""
      }>Business</option>
      <option value="Programming" ${
        device.categories.includes("Business") ? "selected" : ""
      }>Programming</option>
    </select>
  </td>
  <td>
    <input
      class="option-new-input"
      type="text"
      name="img"
      placeholder="ImageUrl"
      value="${device.img}"
      form="form-${device._id}"
      required
    />
  </td>
        <td><button type="submit" class="option-btn-save" form="form-${
          device._id
        }" >Save</button></td>
        <td><button type="button" class="option-btn-delete" onclick="deleteDevice('${
          device._id
        }')" form="form-${device._id}" >X</button></td>
      </form>
    </tr>`;

  devicesList.insertBefore(tr, devicesList.children[1]);
};

function getSelectValues(select) {
  console.log("SELECT", select);
  var result = [];
  var options = select && select.options;
  var opt;

  for (var i = 0, iLen = options.length; i < iLen; i++) {
    opt = options[i];

    if (opt.selected) {
      result.push(opt.value || opt.text);
    }
  }
  console.log("SELECT RESULT", result);
  return result;
}

const getDevices = async () => {
  const res = await axios({
    method: "get",
    url: "/api/devices/",
  });
  console.log("THE RES:", res);
  const { data } = res;
  data.forEach((device) => {
    appendDeviceToList(device);
  });
};

const createDevice = async (event) => {
  event.preventDefault();
  console.log("I will create a new device");

  const data = {
    title: event.target.title.value,
    price: event.target.price.value,
    description: event.target.description.value,
    img: event.target.img.value,
    categories: getSelectValues(event.target.categories.value),
  };

  console.log("DATA TO SEND", data);

  const res = await axios({
    method: "post",
    url: "/api/devices/",
    data: data,
  });

  appendDeviceToList(res.data);
};

const updateDevice = async (event) => {
  event.preventDefault();
  console.log("I will update a device");

  console.log("EVENT", event.target.categories.value);
  const data = {
    title: event.target.title.value,
    price: event.target.price.value,
    description: event.target.description.value,
    img: event.target.img.value,
    categories: getSelectValues(event.target.categories),
  };

  console.log("DATA TO SEND", data);

  const res = await axios({
    method: "put",
    url: `/api/devices/${event.target.deviceId.value}/`,
    data: data,
  });
};

const deleteDevice = async (id) => {
  const res = await axios({
    method: "delete",
    url: `/api/devices/${id}/`,
  });
  if (res.status == 200) {
    const form = document.getElementById(`form-${id}`);
    form.parentElement.remove(form.parentElement);
  }
};

getDevices();
