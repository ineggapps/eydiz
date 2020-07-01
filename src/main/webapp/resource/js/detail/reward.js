$(function () {
  $("input.rewardId").change(function (e) {
    const checked = $(this).prop("checked");
    const $optionWrapper = $(this).closest(".rewardItemBox").find(".rewardOptionWrapper");
    if (checked) {
      $optionWrapper.addClass("active");
    } else {
      $optionWrapper.removeClass("active");
    }
  });
});
