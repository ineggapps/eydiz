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
