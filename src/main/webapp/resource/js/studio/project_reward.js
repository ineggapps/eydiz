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

//배송 옵션
$(function () {
  $("input[name=isShipping]").on("checked", function (e) {
    const val = $(this).val();
    if (val == 1) {
      $(".shippingOptionWrap").addClass("show");
    } else {
      $(".shippingOptionWrap").removeClass("show");
    }
  });
});

// function preventOtherKeys(keyEvent) {
//   const k = keyEvent.key;
//   console.log(/[0-9]/gi.test(k));
//   if (!/[0-9]/gi.test(k)) {
//     console.log("입력 방지");
//     keyEvent.preventDefault();
//   }
// }

//금액 입력하는 부분 숫자 아니면 방지
$(function () {
  $("#amount").keyup(function (e) {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, "")
    );
  });

  $("#shipAmount").keyup(function (e) {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, "")
    );
  });
  $("#limitQuantity").keyup(function (e) {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, "")
    );
  });
});

//리워드 등록하기
function checkRewardForm() {
  //유효성 검사

  return true;
}

$(function () {
  $(".rewardModal .btnSubmit").click(function () {
    if (!checkRewardForm()) {
      return false;
    }
    //리워드 등록 ajax
    const projectNo = $("input[name=projectNo]").val();
    const mode = $(".rewardModal").attr("data-mode");
    var url = cp + "/studio/project/" + projectNo + "/reward";
    if (mode == "edit") {
      const rewardNo = $(".rewardModal").attr("data-reward-no");
      url += "/" + rewardNo + "/update";
    } else {
      url += "/add";
    }
    const $shipAmount = $("input[name=shipAmount]");
    if ($shipAmount.val() == "") {
      $shipAmount.val("0");
    }
    const q = $("form[name=rewardForm]").serialize();
    console.log(projectNo, url, q);
    ajaxJSON(url, "post", q)
      .then(function (data) {
        if (data.result == "ok") {
          loadReward(projectNo);
        }
      })
      .catch(function (e) {
        console.log(e);
      });
  });
});

//리워드 정보 불러오기
function loadReward(projectNo) {
  const $rewardOverlay = $(".rewardOverlay");
  const url = cp + "/studio/project/" + projectNo + "/reward/list";
  ajaxJSON(url, "get", {})
    .then(function (data) {
      if (data.result == "ok") {
        renderReward(data.rewards);
        $rewardOverlay.removeClass("show");
        document.rewardForm.reset();
        $(".rewardModal").removeAttr("data-mode");
        $(".rewardModal").removeAttr("data-reward-no");
        $(".rewardModal .btnSubmit").text("등록");
      }
    })
    .catch(function (e) {
      console.log(e);
    });
}

function renderReward(rewards) {
  const rewardNo = "data-reward-no";
  const $list = $(".rewardSnippetList");
  $list.empty();
  $.each(rewards, function (idx, item) {
    const $o = $(".rewardSnippet.dummy").clone(true);
    $o.removeClass("dummy");
    //기타 정보 입력
    $o.attr(rewardNo, item.rewardNo);
    $o.attr("data-amount", item.amount);
    $o.attr("data-limit-quantity", item.limitQuantity);
    $o.attr("data-is-shipping", item.isShipping);
    $o.attr("data-reward-option", item.rewardOption);
    ////
    $o.find(".dbColumnAmount").text(item.amount.format() + "원");
    $o.find(".dbColumnLimitQuantity").text("제한 수량 " + item.limitQuantity.format() + "개");
    $o.find(".dbColumnTitle").text(item.rewardTitle);
    $o.find(".dbColumnContent").text(item.rewardContent);
    if (item.isShipping == 0) {
      $o.find(".dbColumnShipAmount").text("해당 없음");
    } else {
      $o.find(".dbColumnShipAmount").text(item.shipAmount.format() + "원");
    }
    if (item.startShippingDate) {
      $o.find(".dbColumnShippingStartDate").text(item.startShippingDate);
    } else {
      $o.find(".dbColumnShippingStartDate").text("해당 없음");
    }
    $o.appendTo($list);
    $(".rewardSnippetList > div").wrap("<li></li>");
  });
}

//초기 리워드 목록 호출하기
$(function () {
  const projectNo = $("input[name=projectNo]").val();
  loadReward(projectNo);
});

//리워드 삭제하기
$(function () {
  $("body").on("click", ".btnSnippetDelete", function (e) {
    if (!confirm("정말 삭제하시겠습니까?")) {
      return false;
    }
    const rewardNo = $(this).closest(".rewardSnippet").attr("data-reward-no");
    const projectNo = $("input[name=projectNo]").val();
    const url = cp + "/studio/project/" + projectNo + "/reward/" + rewardNo + "/delete";
    ajaxJSON(url, "post", {})
      .then(function (data) {
        if (data.result == "ok") {
          loadReward(projectNo);
        }
      })
      .catch(function (e) {
        console.log(e);
      });
  });
});

//리워드 편집하기
$(function () {
  $("body").on("click", ".btnSnippetEdit", function () {
    const $rewardOverlay = $(".rewardOverlay");
    const $item = $(this).closest(".rewardSnippet");
    //수정버튼을 바꿔치기...
    $(".rewardModal").attr("data-mode", "edit");
    $(".rewardModal").attr("data-reward-no", $item.attr("data-reward-no"));
    $(".rewardModal .btnSubmit").text("수정");
    //금액, 리워드명, 상세설명, 옵션 조건, 배송조건(배송필요/불필요), 배송비, 제한수량, 발송시작일

    console.log(
      $item.attr("data-amount"),
      $item.find(".dbColumnTitle").text(),
      $item.find(".dbColumnContent").text(),
      $item.attr("data-reward-option"),
      $item.attr("data-limit-quantity"),
      $item.find(".dbColumnShippingStartDate").text()
    );
    $("input[name=amount]").val($item.attr("data-amount"));
    $("input[name=rewardTitle]").val($item.find(".dbColumnTitle").text());
    $("#rewardContent").text($item.find(".dbColumnContent").text());
    if ($item.attr("data-reward-option") == undefined) {
      $("#rewardOptionExist option:eq(0)").prop("selected", "selected");
    } else {
      $("#rewardOptionExist option:eq(1)").prop("selected", "selected");
      $("#rewardOption").closest(".inputWrap").addClass("show");
      $("#rewardOption").text($item.attr("data-reward-option"));
    }

    if ($item.attr("data-is-shipping") == 1) {
      $("input[name=isShipping]").eq(0).prop("checked", "checked");
    } else {
      $("input[name=isShipping]").eq(1).prop("checked", "checked");
    }
    $("input[name=limitQuantity]").val($item.attr("data-limit-quantity"));
    $("input[name=startShippingDate]").val($item.find(".dbColumnShippingStartDate").text());

    $rewardOverlay.addClass("show");
  });
});
