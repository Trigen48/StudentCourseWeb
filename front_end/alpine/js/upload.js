const form = document.getElementById("uploadForm");
form.addEventListener("submit", (e) => {
  e.preventDefault();
  const formData = new FormData(form);
  axios
    .post(`${ServerURL}/upload`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    })
    .then((result) => result.data)
    .then((result) => uploadFileNotify(result))
    .catch((error) => {
      alert(error);
    });
});

function uploadFileNotify(result) {
  alert(result.message);
}
