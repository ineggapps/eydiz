// 숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.format = function () {
  if (this == 0) return 0;

  var reg = /(^[+-]?\d+)(\d{3})/;
  var n = this + "";

  while (reg.test(n)) n = n.replace(reg, "$1" + "," + "$2");

  return n;
};

// 문자열 타입에서 쓸 수 있도록 format() 함수 추가
String.prototype.format = function () {
  var num = parseFloat(this);
  if (isNaN(num)) return "0";

  return num.format();
};

//위의 스크립트 출처: https://stove99.tistory.com/113 [스토브 훌로구]

//ajax
function ajaxJSON(url, method, data) {
  return new Promise(function (resolve, reject) {
    try {
      $.ajax({
        url: url,
        type: method,
        data: data,
        success: function (data) {
          if (data.result == "ok") {
            resolve(data);
          } else {
            reject(data);
          }
        },
        error: function (e) {
          reject(e.responseText);
        },
      });
    } catch (error) {
      reject(error);
    }
  });
}
///

$(".scrollPanel a").click(function (e) {
  scrollCategory();
});

var categoryScrollLeft = 0;
$(function () {
  $(".categoryViewer").scroll(function () {
    categoryScrollLeft = $(this).scrollLeft();
  });
});

function scrollCategory() {
  const $viewer = $(".categoryViewer");
  const $content = $(".categoryContent");
  const length = $content.find("li").length;
  const itemWidth = 100; //여백 포함 한 아이템이 차지하는 공간 가로 너비
  const endItemPos = itemWidth * length; // 맨 끝 아이템의 시작 위치
  const itemCount = Math.floor($viewer.width() / itemWidth); // 아이템이 보이는 너비 / 아이템 하나의 너비 (버림)
  const currentPosition = $content.offset().left;
  const nextItemPosition = itemCount * itemWidth + itemWidth; //다음 아이템 위치
  var scrollTo = currentPosition - nextItemPosition;
  //  console.log(scrollTo, currentPosition, nextItemPosition);

  if (-scrollTo > endItemPos) {
    //그러나 전체 너비를 벗어나면 다시 초기로 되돌림.
    scrollTo = 0;
  }
  $content.css({
    "-webkit-transform": "translate(" + scrollTo + "px, 0)",
    "-moz-transform": "translate(" + scrollTo + "px, 0)",
    "-ms-transform": "translate(" + scrollTo + "px, 0)",
    "-o-transform": "translate(" + scrollTo + "px, 0)",
    transform: "translate(" + scrollTo + "px, 0)",
  });
  categoryScrollLeft = scrollTo;
}

//셀렉트 상자 이벤트
function getSort() {
  return $("#sort").val().trim();
}

function getStatus() {
  return $("#status").val().trim();
}

function getKeyword() {
  return $("#keyword").val().trim();
}

function initializeProjectList() {
  page = 1;
  pageCount = 1;
  clearProjectSnippet();
  loadProjectSnippet(categoryNo, page, getKeyword(), getStatus(), getSort());
}

$(function () {
  $("#status").on("change click", function () {
    initializeProjectList();
  });
  $("#sort").on("change click", function () {
    initializeProjectList();
  });
  $("a.searchButton").on("change click", function () {
    initializeProjectList();
  });
  $("#keyword").on("keypress", function (e) {
    if (e.keyCode == 13) {
      initializeProjectList();
      e.preventDefault();
    }
  });
});

function clearProjectSnippet() {
  const $wrap = $("ul.gridContent").eq(0);
  $wrap.empty();
}

//ajax 프로젝트 불러오기
var page = 1;
var pageCount = 1;
function loadProjectSnippet(categoryNo, page, keyword, status, sort) {
  if (!keyword) {
    keyword = "";
  }
  if (!status) {
    status = "all";
  }
  if (!sort) {
    sort = "recommend";
  }
  const url = cp + "/main/project/snippet";
  const method = "get";
  const query = {
    categoryNo: categoryNo,
    page: page,
    keyword: keyword,
    status: status,
    sort: sort,
    // rows: 9,
  };
  ajaxJSON(url, method, query)
    .then(function (data) {
      pageCount = data.page_count;
      renderSnippet(data.project);
    })
    .catch(function (e) {
      console.log(e);
    });
}

function renderSnippet(items) {
  const $element = $(".item.sample");
  const $wrap = $("ul.gridContent");
  $.each(items, function (idx, item) {
    var $project = $element.clone(true).removeClass("sample");
    if (item.projectImageUrl) {
      $project.find(".thumbnail").css("background-image", "url('" + item.projectImageUrl + "')");
    }
    $project.find(".thumbnail").attr("data-project-no", item.projectNo);
    $project
      .find(".subject a")
      .text(item.projectName)
      .attr("href", cp + "/detail/" + item.projectNo);
    $project.find(".category").text(item.categoryName);
    $project.find(".name").text(item.name);
    var rate = item.attainRate * 100 <= 100 ? item.attainRate * 100 : 100;
    $project.find(".progressBar").css({ width: rate + "%" });
    $project.find(".percent").text(parseFloat(item.attainRate * 100).toFixed(2) + "%");
    $project.find(".totalAmount").text(item.totalAmount.format() + "원");

    var s;
    if (item.remainDays > 0) {
      s = item.remainDays + "일 남음";
    } else if (item.remainDays == 0) {
      s = "오늘 마감";
    } else if (item.remainDays < 0) {
      s = "마감";
    }
    $project.find(".remainDays").text(s);
    $project.appendTo($wrap);
  });
}

//프로젝트 불러오기 등..
$(function () {
  loadProjectSnippet(categoryNo, page++, null, null, null);

  //스크롤 이벤트
});
$(window).scroll(function () {
  var scrollTop = $(window).scrollTop();
  var height = $(document).height() - $(window).height() - 100; //- 여분
  //console.log(scrollTop, height, "...", page, pageCount);
  if (isVisibleScrollBar() && scrollTop >= height && page <= pageCount) {
    //console.log("... 다음 호출!!!");
    loadProjectSnippet(categoryNo, page++, getKeyword(), getStatus(), getSort());
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
