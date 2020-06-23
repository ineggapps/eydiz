// 숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.format = function () {
  if (this == 0) return 0;

  var reg = /(^[+-]?\d+)(\d{3})/;
  var n = this + "";

  while (reg.test(n)) n = n.replace(reg, "$1" + "," + "$2");

  return n;
};

// 문자열 타입에서 쓸 수 있도록 format() 함수 추가
String.prototype.format = function () {
  var num = parseFloat(this);
  if (isNaN(num)) return "0";

  return num.format();
};

// 출처: https://stove99.tistory.com/113 [스토브 훌로구]

//리워드 추가 버튼 클릭
$(function () {
  const $rewardOverlay = $(".rewardOverlay");
  $("#btnAddReward").click(function () {
    $rewardOverlay.addClass("show");
  });

  $(".btnRewardClose").click(function () {
    $rewardOverlay.removeClass("show");
  });
});

//옵션 있는 경우
$(function () {
  const $rewardOptionExist = $("#rewardOptionExist");
  const $rewardOption = $("#rewardOption").closest(".inputWrap");
  $rewardOptionExist.on("change click blur", function () {
    const val = $(this).val();
    if (val == 1) {
      $rewardOption.removeClass("hide");
    } else {
      $rewardOption.addClass("hide");
    }
  });

  const $isShipping = $("input[name=isShipping]");
  const $shippingOptionWrap = $(".shippingOptionWrap");
  $isShipping.on("change", function () {
    const val = $(this).val();
    if (val == 1) {
      $shippingOptionWrap.addClass("show");
    } else {
      $shippingOptionWrap.removeClass("show");
    }
  });
});

//리워드 등록하기
function checkRewardForm() {
  alert("통과");
  return true;
}

$(function () {
  $(".rewardModal .btnSubmit").click(function () {
    if (!checkRewardForm()) {
      return false;
    }
    //리워드 등록 ajax
    const projectNo = $("input[name=projectNo]").val();
    const url = cp + "/studio/project/" + projectNo + "/reward/add";
    const q = $("form[name=rewardForm]").serialize();
    console.log(projectNo, url, q);
    ajaxJSON(url, "post", q);
  });
});

//리워드 정보 불러오기
function loadReward(projectNo, brandNo) {
  const url = cp + "/studio/project/" + projectNo + "/reward/list";
  ajaxJSON(url, "get", {})
    .then(function (data) {
      if (data.result == "ok") {
        renderReward(data.rewards);
      }
    })
    .catch(function (e) {
      console.log(e);
    });
}

function renderReward(rewards) {
  const rewardNo = "data-reward-no";
  const $list = $(".rewardSnippetList");
  $.each(rewards, function (idx, item) {
    const $o = $(".rewardSnippet.dummy").clone(true);
    $o.removeClass("dummy");
    $o.attr(rewardNo, item.rewardNo);
    $o.find(".dbColumnAmount").text(item.amount.format() + "원");
    $o.find(".dbColumnLimitQuantity").text("제한 수량 " + item.limitQuantity.format() + "개");
    $o.find(".dbColumnTitle").text(item.rewardTitle);
    $o.find(".dbColumnContent").text(item.rewardContent);
    if (item.isShipping == 0) {
      $o.find(".dbColumnShippingAmount").text("해당 없음");
    } else {
      $o.find(".dbColumnAmount").text(item.shipAmount.format() + "원");
    }
    $o.appendTo($list);
    $(".rewardSnippetList > div").wrap("<li></li>");
  });
}

$(function () {
  const projectNo = $("input[name=projectNo]").val();
  const brandNo = $("input[name=brandNo]").val();
  loadReward(projectNo, brandNo);
});
