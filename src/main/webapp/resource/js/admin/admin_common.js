//AJAX 관련 메서드 여기에 작성하고 사용하기


/////홈페이지 메뉴에 관한 내용

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

//삭제 버튼 클릭
$(function () {
  $(".btnDeleteProject").click(function () {
    if (!confirm("정말 프로젝트를 삭제하시겠습니까?")) {
      return false;
    }
    const $item = $(this);
    const projectNo = $(this).closest("li").attr("data-project-no");
    const url = cp + "/studio/project/" + projectNo + "/delete";
    ajaxJSON(url, "post", {})
      .then(function (data) {
        if (data.result == "ok") {
          alert("삭제되었습니다.");
          $item.closest("li").remove();
        }
      })
      .catch(function (e) {
        console.log(e);
      });
  });
});
