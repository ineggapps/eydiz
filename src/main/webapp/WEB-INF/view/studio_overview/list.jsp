<%@ page contentType="text/html; charset=UTF-8"%> <%@ page trimDirectiveWhitespaces="true"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <% String cp = request.getContextPath(); %>
<ul class="projectList"></ul>
<ul class="projectList sample">
  <li class="sample" data-project-no="">
    <div class="projectItem">
      <button type="button" class="btnDeleteProject"><span class="hidden">지우기</span></button>
      <div class="projectThumbnail">
        <div class="projectOverlay">
          <span class="whiteSubtitle">대표이미지 등록 필요</span>
        </div>
        <div class="projectBackground"></div>
      </div>
      <div class="projectDescription">
        <dl>
          <dt class="projectName"></dt>
          <dd class="projectBrandName"></dd>
        </dl>
        <div class="projectStatus">
          <p><i class="projectStatus"></i> <span>status...</span></p>
        </div>
      </div>
      <div class="projectItemController">
        <a href="#" class="btnManage">스튜디오 바로가기</a
        >
      </div>
    </div>
  </li>
</ul>
<script>
//ajax list 불러오기 무한스크롤 적용
var page = 1;
var pageCount = 1;
function loadList(p) {
  var url = cp + "/studio/project/ajax/list/" + p;
  ajaxJSON(url, "get", {})
    .then(function (data) {
      pageCount = data.page_count;
      renderList(data.project);
    })
    .catch(function (e) {
      console.log(e);
    });
}

function renderList(items) {
  $wrap = $("ul.projectList");
  $wrapSample = $("ul.projectList.sample");
  $.each(items, function (idx, item) {
    $item = $wrapSample.find("li.sample").clone(true).attr("data-project-no", item.projectNo).removeClass("sample");
    $item.find(".projectBackground").css("background-image", "url('"+item.projectImageUrl+"')");
    $item.find(".projectName").text(item.projectName ? item.projectName : "제목을 입력하세요");
    $item.find(".brandName").text(item.brandName);
    $projectStatus = $item.find("div.projectStatus");
    switch (item.statusNo) {
      case 0:
        $projectStatus.find("i.projectStatus").addClass("ready");
        $projectStatus.find("span").text("펀딩 준비 작성 중");
        break;
      case 1:
        $projectStatus.find("i.projectStatus").addClass("judge");
        $projectStatus.find("span").text("펀딩 심사 중");
        break;
      case 2:
        $projectStatus.find("i.projectStatus").addClass("deny");
        $projectStatus.find("span").text("반려");
        break;
      case 3:
        $projectStatus.find("i.projectStatus").addClass("report");
        $projectStatus.find("span").text("신고 접수된 프로젝트");
        break;
      case 4:
        $projectStatus.find("i.projectStatus").addClass("blind");
        $projectStatus.find("span").text("블라인드됨");
        break;
      case 5:
        $projectStatus.find("i.projectStatus").addClass("activate");
        $projectStatus.find("span").text("진행 중");
        break;
      case 6:
        $projectStatus.find("i.projectStatus").addClass("expire");
        $projectStatus.find("span").text("만료됨");
        break;
    }
    $item.find(".btnManage").attr("href", cp + "/studio/project/" + item.projectNo + "/dashboard");
    $item.appendTo($wrap);
  });
}

$(function () {
  loadList(page++);
  
  $("body").on("click", ".projectList li", function(e){
	  goToLocation(this);
  });
});

$(window).scroll(function () {
  var scrollTop = $(window).scrollTop();
  var height = $(document).height() - $(window).height() - 100; //- 여분
  console.log(scrollTop, height, "...", page, pageCount);
  if (isVisibleScrollBar() && scrollTop >= height && page <= pageCount) {
    loadList(page++);
  }
});

function isVisibleScrollBar() {
  //true:스크롤바 있음, false: 스크롤바 없음
  return $("body").height() >= $(window).height();
}

function goToLocation(element) {
  const projectNo = $(element).attr("data-project-no");
  location.href = cp + "/studio/project/" + projectNo + "/dashboard";
}

</script>