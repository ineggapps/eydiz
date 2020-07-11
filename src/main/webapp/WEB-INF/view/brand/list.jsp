<%@ page contentType="text/html; charset=UTF-8"%> <%@ page trimDirectiveWhitespaces="true"%> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%> <% String cp = request.getContextPath(); %>
<div class="emptyMessage sample">아직 펀딩한 프로젝트가 없습니다.</div>
<ul class="projectList"></ul>
<ul class="projectList sample">
  <li class="sample" data-project-no="">
    <div class="projectItem">
      <div class="projectThumbnail">
        <div class="projectOverlay">
          <span class="whiteSubtitle">이미지 준비 중입니다.</span>
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
        <p class="categoryName">카테고리 이름</p>
      </div>
    </div>
  </li>
</ul>
<script>
//ajax list 불러오기 무한스크롤 적용
var page = 1;
var pageCount = 1;
function loadList(p) {
  var url;
  var location = window.location.href;
  if(location.indexOf("/brand/funded")>0){
	  url = cp + "/brand/ajax/funded/" + brandNo + "/page/" + p;
  }
  else{
	  url = cp + "/brand/ajax/made/" + brandNo + "/page/" + p;
  }
  
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
  if(!items.length){
	  $(".emptyMessage").removeClass("sample");
	  return;
  }
  $.each(items, function (idx, item) {
    $item = $wrapSample.find("li.sample").clone(true).attr("data-project-no", item.projectNo).removeClass("sample");
    $btnDelete = $item.find(".btnDeleteProject");
    if(item.statusNo!=0 && item.statusNo!=2){
    	$btnDelete.remove();
    }
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
    $item.find(".categoryName").text(item.categoryName);
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
  //console.log(scrollTop, height, "...", page, pageCount);
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
  location.href = cp + "/detail/" + projectNo;
}

</script>