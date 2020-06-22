
//리워드 추가 버튼 클릭
$(function(){
	const $rewardOverlay = $(".rewardOverlay");
	$("#btnAddReward").click(function(){
		$rewardOverlay.addClass("show");
	});
	
	$(".btnRewardClose").click(function(){
		$rewardOverlay.removeClass("show");
	});
	
});