$("#memberId").keypress(function (key) {
  if (key.keyCode == 13 && $(this).val().trim()) {
    toFocus("#memberPwd");
  }
});

$("#memberPwd").keypress(function (key) {
  if (key.keyCode == 13) {
    const id = $("#memberId").val().trim();
    const pwd = $("#memberPwd").val();
    if (id && pwd) {
      toFocus("#btnLogin");
      $("#btnLogin").click();
    }
  }
});

$("#btnLogin").click(function () {
  const f = document.loginForm;
  f.submit();
});

function toFocus(target) {
  $(target).focus();
}
