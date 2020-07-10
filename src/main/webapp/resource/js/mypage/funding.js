/*ajax*/
var page = 1;
var pageCount = 1;
function loadMyBoughtProjects(p) {
  if (p == 0) {
    return;
  }
  const url = cp + "/mypage/history/" + p;
  ajaxJSON(url, "get", null)
    .then(function (data) {
      if (data.result == "ok") {
        pageCount = data.page_count;
        if (pageCount == 0 || pageCount == page - 1) {
          $(".mypage.newsMore").remove();
          page = 0;
        }
        renderItems(data.projects);
      }
    })
    .catch(function (e) {
      console.log(e);
    });
}

function renderItems(items) {
  $sample = $(".historyFundingList li.sample");
  $.each(items, function (idx, item) {
    $item = $sample.clone(true).removeClass("sample");
    $item.find(".historyCategory").text(item.categoryName);
    $item.find(".historyProjectName").text(item.projectName);
    $item.find(".historyBrandName").text("by " + item.brandName);
    const statusLabel = item.statusNo == 5 ? "진행 중" : "종료";
    const statusClassName = item.statusNo == 5 ? "activate" : "expire";
    $item.find(".historyProjectStatus span").text(statusLabel);
    $item.find(".historyProjectStatus i").addClass(statusClassName);
    $item.find(".historyDate").text(item.boughtDate);
    $item.find(".historyStatus").text(item.isCanceled == 0 ? "결제 완료" : "취소 완료");
    $item.appendTo("ul.historyFundingList");
  });
}

$(function () {
  loadMyBoughtProjects(page++);

  $(".mypage.newsMore").click(function () {
    loadMyBoughtProjects(page++);
  });
});
