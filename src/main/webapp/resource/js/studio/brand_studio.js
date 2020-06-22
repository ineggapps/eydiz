$(function () {
  //오버레이 창이 떴을 때 스크롤 막기
  $(".overlay").on("scroll touchmove mousewheel", function (e) {
    if ($(this).hasClass("show")) {
      e.preventDefault();
    }
  });
});

//모바일 앱 버튼 클릭
$(function () {
  $("#btnGnbMenu").click(function () {
    $(".columnSide").addClass("show");
    $(".overlay").addClass("show");
  });

  $(".overlay").click(function () {
    $(".columnSide").removeClass("show");
    $(".overlay").removeClass("show");
  });
});

//확장버튼 클릭
$(function () {
  $(".menuName").click(function () {
    const className = "extended";
    $li = $(this).closest("li");
    if ($li.hasClass(className)) {
      $li.removeClass(className);
    } else {
      $li.addClass(className);
    }
  });
});

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