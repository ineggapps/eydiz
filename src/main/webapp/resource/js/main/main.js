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
  const itemWidth = 100;
  const itemCount = Math.floor($viewer.width() / itemWidth); // 아이템이 보이는 너비 / 아이템 하나의 너비 (버림)
  const currentPosition = $content.offset().left;
  const nextItemPosition = itemCount * itemWidth + itemWidth; //다음 아이템 위치
  var scrollTo = currentPosition - nextItemPosition;
//  console.log(scrollTo, currentPosition, nextItemPosition);

  if (-scrollTo > $content.width()) {
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
