///countup
function isInt(n) {
  if ((n % 1) * 10 === 0) {
    return true;
  }
  return Number(n) === n && n % 1 === 0;
}
function isFloat(n) {
  return Number(n) === n && n % 1 !== 0;
}
var options = {
  useEasing: true,
  useGrouping: true,
  separator: ",",
  decimal: ".",
  prefix: "",
  suffix: "",
};
$(function () {
  try {
    var elements = ["cuAttainRate", "cuTotalAmount", "cuSupportCount", "cuRemainDays"];
    for (var i = 0; i < elements.length; i++) {
      var num = $("#" + elements[i]).text();
      var counter = new CountUp(
        elements[i],
        0,
        num,
        isInt(num) ? 0 : 1,
        (i - 1) * 1.4 + 2,
        options
      );
      counter.start();
    }
  } catch (error) {
    console.log(error);
  }
});

////
function ajaxJSON(url, method, data) {
  return new Promise(function (resolve, reject) {
    try {
      $.ajax({
        url: url,
        type: method,
        data: data,
        success: function (data) {
          if (data.result == "ok") {
            resolve(data);
          } else {
            reject(data);
          }
        },
        error: function (e) {
          reject(e.responseText);
        },
      });
    } catch (error) {
      reject(error);
    }
  });
}

function ajaxJSONFile(url, method, data) {
  return new Promise(function (resolve, reject) {
    try {
      $.ajax({
        url: url,
        type: method,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        data: data,
        dataType: "json",
        success: function (data) {
          if (data.result == "ok") {
            resolve(data);
          } else {
            reject(data);
          }
        },
        beforeSend: function (jqXHR) {
          jqXHR.setRequestHeader("AJAX", "true");
        },
        error: function (e) {
          reject(e.responseText);
        },
      });
    } catch (error) {
      reject(error);
    }
  });
}

function toggleLike() {
  const url = cp + "/detail/" + projectNo + "/like";
  const count = parseInt($("#btnLike span.count").text());
  ajaxJSON(url, "post", {})
    .then(function (data) {
      if (data.result == "ok") {
        if (data.like_result == true) {
          $("#btnLike span.icon").addClass("on");
          $("#btnLike span.count").text(count + 1);
        } else {
          $("#btnLike span.icon").removeClass("on");
          $("#btnLike span.count").text(count - 1);
        }
      }
    })
    .catch(function (e) {
      console.log(e.responseText);
      if (e.message == null) {
        location.href = cp + "/member/login";
      }
    });
}

$(function () {
  $("#btnLike").click(function () {
    toggleLike();
  });
});

$(function () {
  $(".rewardItem").click(function () {
    const rewardNo = $(this).attr("data-reward-no");
    const url = cp + "/reward/" + projectNo + "/step1?rewardNo=" + rewardNo;
    location.href = url;
  });
});
