function flushOptionWrapper() {
  $(".rewardNo").each(function (idx, item) {
    console.log(item);
    const checked = $(item).prop("checked");
    const $obj = $(item).closest(".rewardItemBox").find(".rewardOptionWrapper");
    if (checked) {
      $obj.addClass("active");
    } else {
      $obj.removeClass("active");
    }
  });
}

$(function () {
  $("input.rewardNo").change(function (e) {
    const checked = $(this).prop("checked");
    const $optionWrapper = $(this).closest(".rewardItemBox").find(".rewardOptionWrapper");
    if (checked) {
      $optionWrapper.addClass("active");
    } else {
      $optionWrapper.removeClass("active");
    }
  });
  flushOptionWrapper();
});

//입력 체크
$(function () {
  $(".btnSubmit.step1").click(function (e) {
    //step1 다음단계로
    var $inputs = $("input.optionAnswer");
    //#1. 체크한 리워드가 하나도 없을 경우
    const checkedCount = $("input.rewardNo:checked").length;
    if (checkedCount == 0) {
      alert("리워드를 최소 한 개 이상 선택하셔야 합니다.");
      e.preventDefault();
    }
    $inputs.each(function (idx, item) {
      const chk = $(this).closest(".rewardItemBox").find("input.rewardNo").prop("checked");
      const v = $(this).val().trim();

      //#2. 체크한 리워드의 옵션을 기입하지 않은 경우
      if (chk && !v) {
        alert("선택하신 리워드는 옵션을 기입하셔야 합니다.");
        e.preventDefault();
        return false;
      }
    });
  });
});
