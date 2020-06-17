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
      $loadingBar.addClass("show");
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
          $loadingBar.removeClass("show");
          reject(e.responseText);
        },
      });
    } catch (error) {
      $loadingBar.removeClass("show");
      reject(error);
    }
  });
}

// 아이디
$(function () {
  $("#memberId").on("focus blur", function (e) {
    const id = $(this).val().trim().toLowerCase();
    $(this).val(id);
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

// 이메일
$(function () {
  $("#memberEmail").on("focus blur", function (e) {
    const email = $(this).val().trim().toLowerCase();
    $(this).val(email);
    const url = cp + "/member/join/checkEmail";
    if (!email) {
      return;
    }
    if (!isValidEmail(email)) {
      printError($("#memberEmail"), "이메일 주소를 올바르게 입력해 주세요.");
      return;
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

// 비밀번호 ,비밀번호 확 인

$(function () {
  $("#memberPwd").on("change keyup blur", function (e) {
    const pwd = $(this).val();
    if (!pwd) {
      return;
    }

    if (pwd.length < 10) {
      printError($(this), "비밀번호를 10자 이상 입력하세요.");
    } else {
      printDesc($(this), "이 비밀번호는 사용이 가능합니다.");
    }
  });

  $("#memberPwdConfirm").on("change keyup blur", function (e) {
    const pwd = $("#memberPwd").val();
    const pwdConfirm = $(this).val();
    if (!pwdConfirm) {
      return;
    }

    if (pwdConfirm != pwd) {
      printError($(this), "비밀번호가 일치하지 않습니다.");
    } else {
      printDesc($(this), "비밀번호가 일치합니다.");
    }
  });
});

//닉네임
$(function () {
  $("#memberNickname").on("change focus keyup blur", function (e) {
    $(this).val($(this).val().trim());
    const nickname = $(this).val().trim();
    if (!nickname) {
      printError($(this), "닉네임이 입력되지 않았습니다.");
    } else {
      printDesc($(this), nickname + "로 사용하시겠습니까?");
    }
  });
});

$(function () {
  $(".inputBox").on("blur", function (e) {
    validateForm();
  });
});

//유효성 검사
function validateForm() {
  const inputBoxies = $("#wrap").find(".inputBox");
  $("#btnSubmit").attr("disabled", "disabled").addClass("disabled");
  $.each(inputBoxies, function (idx, item) {
    if ($(item).val().trim().length == 0) {
      return false;
    }
    if ($(item).hasClass("error")) {
      return false;
    }
  });
  $("#btnSubmit").removeAttr("disabled").removeClass("disabled");
  return true;
}

//제출
$(function () {
  $("#btnSubmit").click(function () {
    if ($(this).hasClass("disabled")) {
      return;
    }
    $(this).closest("form").submit();
  });
});
