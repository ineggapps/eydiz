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
