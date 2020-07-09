var page = 1;
var pageCount = 1;
function loadNews(page) {
  const url = cp + "/detail/" + projectNo + "/news/view";
  const q = { page: page };
  ajaxJSON(url, "get", q)
    .then(function (data) {
      var news = data.news;
      pageCount = data.page_count;
      if (pageCount == 0 || pageCount == page) {
        $(".newsMore").remove();
      }
      if (pageCount == 0) {
        news = [{
          title: "아직 새소식이 없습니다.",
          content:
            "새소식이 없어서 당황하셨나요? 아직 판매자가 준비중이므로 소식이 나올 때까지 기다려 주세요!",
        }];
      }
      $.each(news, function (idx, item) {
        addItem(item);
      });
    })
    .catch(function (e) {
      console.log(e);
    });
}

function addItem(itemJson) {
  var $wrap = $(".newsWrap");
  var $hr = $wrap.find(".divider.deeper.sample");
  var $sample = $wrap.find(".newsItem.sample");
  var $item = $sample.clone(true);

  $item.find("p.number").text("#" + (Number(page) - 1));
  $item.find(".newsTitle").text(itemJson.title);
  $item.find(".newsContent").html(itemJson.content);

  $hr.clone().appendTo($wrap).removeClass("sample");
  $item.appendTo($wrap).removeClass("sample");
}

$(function () {
  loadNews(page++);

  $(".btnNewsMore").click(function (e) {
    loadNews(page++);
  });
});

// $(window).scroll(function () {
//   var scrollTop = $(window).scrollTop();
//   var height = $(document).height() - $(window).height() - 100; //- 여분
//   console.log(isVisibleScrollBar(), scrollTop, height, "...", page, pageCount);
//   if (isVisibleScrollBar() && scrollTop >= height && page <= pageCount) {
// 	  console.log("ajax 호출");
//     loadNews(page++);
//   }
// });

function isVisibleScrollBar() {
  //true:스크롤바 있음, false: 스크롤바 없음
  return $("body").height() >= $(window).height();
}
