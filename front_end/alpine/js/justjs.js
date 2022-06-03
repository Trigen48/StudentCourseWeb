function PostData(data, postpage, onfail, onsuccess) {
  $.ajax({
    url: postpage,
    dataType: "json",
    type: "POST",
    //contentType: 'application/json',
    data: { jdata: JSON.stringify(data) },

    success: function (data1, textStatus, jQxhr) {
      if (onsuccess != null) {
        onsuccess(data1);
      }
    },
    error: function (jqXhr, textStatus, errorThrown) {
      if (onfail != null) {
        onfail();
      }
    },
  });
}

function PostDataRaw(data, postpage, onfail, onsuccess) {
  $.ajax({
    url: postpage,
    type: "post",
    data: { jdata: JSON.stringify(data) },
    success: function (data1, textStatus, jQxhr) {
      if (onsuccess != null) {
        onsuccess(data1);
      }
    },
    error: function (jqXhr, textStatus, errorThrown) {
      if (onfail != null) {
        onfail();
      }
    },
  });
}

function PostDataCompact(data, postpage, onResponse) {
  $.ajax({
    url: postpage,
    dataType: "json",
    type: "post",
    data: { jdata: JSON.stringify(data) },
    success: function (data1, textStatus, jQxhr) {
      if (onResponse != null) {
        onResponse(data1, 1);
      }
    },
    error: function (jqXhr, textStatus, errorThrown) {
      if (onResponse != null) {
        onResponse(null, 0);
      }
    },
  });
}
