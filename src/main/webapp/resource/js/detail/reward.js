function flushOptionWrapper() {
  $(".rewardNo").each(function (idx, item) {
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

$(function () {
  $("input.requestQuantity").on("keyup", function (e) {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, "")
    );
  });
});

//입력 체크
$(function () {
  $(".btnSubmit.step1").click(function (e) {
    //step1 다음단계로
    var $items = $(".rewardItemBox");
    //#1. 체크한 리워드가 하나도 없을 경우
    const checkedCount = $("input.rewardNo:checked").length;
    if (checkedCount == 0) {
      alert("리워드를 최소 한 개 이상 선택하셔야 합니다.");
      e.preventDefault();
      return false;
    }
    $items.each(function (idx, item) {
      console.log(item, chk, "현재... 아이템");
      var chk = $(this).find("input.rewardNo").prop("checked");
      var $reqQuantity = $(this).find("input.requestQuantity");
      var $optionAns = $(this).find("input.optionAnswer");
      var optionAnswer = $optionAns.val();

      //console.log(chk, item, "요구수량:", $reqQuantity.val(), "옵션:", $optionAns, optionAnswer);

      //#2. 체크한 리워드의 옵션을 기입하지 않은 경우
      if (chk) {
        if (!$reqQuantity.val()) {
          alert("선택하신 리워드의 구매 수량을 기입하시기 바랍니다.");
          $reqQuantity.focus();
          e.preventDefault();
          return false;
        }
        if ($optionAns.length > 0 && !optionAnswer) {
          alert("선택하신 리워드는 옵션을 기입하셔야 합니다.");
          e.preventDefault();
          return false;
        }
        
        //#3. 체크한 리워드 수량이 0개인 경우
        if($reqQuantity.val()<=0){
        	alert("선택하신 리워드 수량은 최소 1개 이상이어야 합니다.");
        	e.preventDefault();
        	return false;
        }

        //#4. 체크한 리워드가 잔여 수량을 초과한 경우
        const remainQuantity = $(this).closest(".rewardItemBox").find("input.remainQuantity").val();
        const requestQuantity = $reqQuantity.val();
        const after = remainQuantity - requestQuantity;
        if (after < 0) {
          alert("선택하신 리워드는 " + remainQuantity + "개 까지만 가능합니다.");
          $reqQuantity.val("");
          $reqQuantity.focus();
          e.preventDefault();
          return false;
        }
      }
    });
  });
});
