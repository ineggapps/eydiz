function flushOptionWrapper(){
	$(".rewardNo").each(function(idx, item){
		console.log(item);
		const checked = $(item).prop("checked");
		const $obj = $(item).closest(".rewardItemBox").find(".rewardOptionWrapper");
		if(checked){
			$obj.addClass("active");
		}else{
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
