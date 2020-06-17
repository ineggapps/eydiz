function isValidId(id) {
  return /^[a-z0-9_]{4,15}$/.test(id);
}

function isValidEmail(email) {
  var format = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
  return format.test(email); // true : 올바른 포맷 형식
}

function hide(inputBoxSelector) {
  $(inputBoxSelector).removeClass("error");
}

function printError($inputBoxSelector, text) {
  $inputBoxSelector.addClass("error");
  const $span = $inputBoxSelector.closest("label").find(".desc").removeClass("show");
  $span.text(text);
  console.log(text);
}

function printDesc($inputBoxSelector, text) {
  $inputBoxSelector.removeClass("error");
  const $span = $inputBoxSelector.closest("label").find(".desc").addClass("show");
  $span.text(text);
  console.log(text);
}

function ajaxJSON(url, method, data, $inputBoxSelector) {
  return new Promise(function (resolve, reject) {
    try {
      $loadingBar = $inputBoxSelector.closest("label").find(".loadingBar");
      $loadingBar.toggleClass("show");
      $.ajax({
        url: url,
        type: method,
        data: data,
        success: function (data) {
          $loadingBar.toggleClass("show");
          if (data.result == "ok") {
            resolve(data);
          } else {
            reject("유효하지 않은 요청... 파라미터를 확인하세요");
          }
        },
        error: function (e) {
          $loadingBar.toggleClass("show");
          reject(e.responseText);
        },
      });
    } catch (error) {
      $loadingBar.toggleClass("show");
      reject(error);
    }
  });
}

//아이디
$(function () {
  $("#memberId").on("focus blur", function (e) {
    const id = $(this).val().trim();
    const url = cp + "/member/join/checkId";
    if (!id) {
      return;
    }

    if (!isValidId(id)) {
      printError($(this), "아이디는 소문자와 숫자 통틀어 15자까지만 입력이 가능합니다.");
      return;
    } else {
      hide($(this));
    }

    const query = { memberId: id };
    ajaxJSON(url, "post", query, $(this))
      .then(function (data) {
        if (data.taken == "false") {
          printDesc($("#memberId"), "사용할 수 있는 아이디입니다.");
        } else {
          printError($("#memberId"), "이미 사용 중인 아이디입니다.");
        }
      })
      .catch(function (e) {
        alert("서버와의 통신 오류..." + e);
      });
  });
});

//이메일
$(function () {
  $("#memberEmail").on("focus blur", function (e) {
    const email = $(this).val().trim();
    const url = cp + "/member/join/checkEmail";
    if (!email) {
      return;
    }
    if (!isValidEmail(email)) {
      printError($("#memberEmail"), "이메일 주소를 올바르게 입력해 주세요.");
    } else {
      hide($("#memberEmail"));
    }
    const query = { memberEmail: email };
    ajaxJSON(url, "post", query, $(this))
      .then(function (data) {
        const result = data.taken;
        if (result == "false") {
          printDesc($("#memberEmail"), "사용할 수 있는 이메일입니다.");
        } else {
          printError($("#memberEmail"), "이미 사용 중인 이메일입니다.");
        }
      })
      .catch(function (e) {
        console.log("서버와의 통신 오류입니다." + e);
      });
  });
});
