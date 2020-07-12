//모달창 제어
const $overlay = $(".modalOverlay");
function openModal() {
  $overlay.addClass("show");
}
function closeModal() {
  $overlay.removeClass("show");
}
$(function () {
  $("#btnCommentModal").click(function () {
    openModal();
  });
  $overlay.find(".btnClose").click(function () {
    closeModal();
  });
});

//답글버튼
$(function () {
  $("body").on("click", ".btnReply", function () {
    const $replyArea = $(this).closest(".commentArea").find(".commentReplyWrap");
    const $replyInput = $(this).closest(".commentArea").find(".commentInput.reply");
    if ($replyArea.hasClass("hide")) {
      $replyArea.removeClass("hide");
      $replyInput.removeClass("hide");
    } else {
      $replyArea.addClass("hide");
      $replyInput.addClass("hide");
    }
  });
});
//댓글 영역 비우기
function clearComments() {
  const $commentWrap = $(".commentWrap");
  const $commentItem = $commentWrap.find(".parent.commentItem.hide").eq(0).clone(true);
  $commentWrap.empty();
  $commentWrap.prepend($commentItem);
}
// 댓글 불러오기
function loadComment() {
  clearComments();
  const url = cp + "/detail/" + projectNo + "/community/view";
  const method = "post";
  const query = "projectNo=" + projectNo;
  const $wrap = $(".commentWrap");
  const $target = $(".parent.commentItem");
  ajaxJSON(url, method, query)
    .then(function (data) {
      var $element;
      const comments = data.comments;
      $.each(comments, function (idx, item) {
        $element = $target.clone(true).appendTo($wrap).removeClass("hide");
        $element.attr("data-comment-no", item.commentNo);
        renderComment($element, item, ".parent");
        // 답글 개수 등...
        const replyCount = item.replyCount; // 답글 개수
        const replyQuery = query + "&parentCommentNo=" + item.commentNo;
        if (replyCount > 0) {
          loadReplyComment(item.commentNo, $element);
        }
      });
    })
    .catch(function (e) {
      console.log(e);
    });
}
//답글 불러오기
function loadReplyComment(commentNo, $element) {
  const url = cp + "/detail/" + projectNo + "/community/view";
  const method = "post";
  const query = { projectNo: projectNo, parentCommentNo: commentNo };
  const $wrap = $element.find(".commentReplyWrap");
  const $target = $element.find(".reply.commentItem.hide");
  ajaxJSON(url, method, query)
    .then(function (data) {
      const comments = data.comments;
      $.each(comments, function (idx, item) {
        $element = $target.clone(true).appendTo($wrap).removeClass("hide");
        $element.attr("data-comment-no", item.commentNo);
        $element.attr("data-parent-comment-no", item.parentCommentNo);
        renderComment($element, item);
      });
    })
    .catch(function (e) {
      console.log(e);
    });
}
//댓글 렌더링
function renderComment($element, jsonItem, prefix) {
  if (prefix == undefined) {
    prefix = "";
  }
  if (jsonItem.memberImageUrl) {
    $element
      .find(prefix + ".commentAvatar")
      .eq(0)
      .css("background-image", 'url("' + jsonItem.memberImageUrl + '")');
  }
  $element
    .find(prefix + ".author")
    .eq(0)
    .text(jsonItem.memberNickname);
  $element
    .find(prefix + ".date")
    .eq(0)
    .text(jsonItem.createdDate);
  $element
    .find(prefix + ".commentContent")
    .eq(0)
    .text(jsonItem.content);
}
$(function () {
  loadComment();
});

// 댓글쓰기
function submitComment(query) {
  const url = cp + "/detail/" + projectNo + "/community/create";
  return ajaxJSON(url, "post", query);
}
$(function () {
  const $modalForm = $("form[name=modalForm]");
  $overlay.find(".comment.btnSubmit").click(function () {
    query = $modalForm.serialize();
    submitComment(query)
      .then(function (data) {
        if (data.result == "ok") {
          alert("댓글을 작성했습니다.");
          loadComment();
          closeModal();
        }
      })
      .catch(function (e) {
        console.log(e);
      });
  });
});

// 답글 쓰기
$(function () {
  // focus, blur
  const $commentWrap = $(".commentWrap");
  const $commentInputContent = $(".commentInputContent");
  const $textarea = $commentInputContent.find("textarea");
  $commentWrap.on("click focus", ".commentInputContent textarea", function () {
    $(this).closest(".commentInputContent").addClass("focus");
  });
  $commentWrap.on("focusout", ".commentInputContent textarea", function () {
    const content = $(this).val();
    if (!content.trim()) {
      $(this).closest(".commentInputContent").removeClass("focus");
      $(this).css("height", "32px");
    }
  });
  //등록버튼
  $commentWrap.on("click", ".reply.btnSubmit", function () {
    const $wrap = $(this).closest(".commentArea").find(".commentReplyWrap");
    const $commentItem = $(this).closest(".commentArea").find(".hide.commentItem");
    const $content = $(this).closest(".commentInputContent").find("textarea");
    const content = $(this).closest(".commentInputContent").find("textarea").val();
    $content.val("");
    const parentCommentNo = $(this).closest(".parent.commentItem").data("comment-no");
    const q = { parentCommentNo: parentCommentNo, content: content };
    submitComment(q)
      .then(function (data) {
        if (data.result == "ok") {
          alert("답글을 작성했습니다.");
          //fake comment
          const $element = $commentItem.clone(true).appendTo($wrap).removeClass("hide");
          renderComment($element, {
            memberNickname: memberNickname,
            date: new Date(),
            content: content,
          });
        }
      })
      .catch(function (e) {
        console.log(e);
      });
  });
});

//댓글 답글관리 (수정,삭제 팝업)
$(function(){
	$(".commentMenu").click(function(){
		$context = $(this).closest(".commentHeader").find(".commentContext");
		if($context.hasClass("hide")){
			$context.removeClass("hide");
		}else{
			$context.addClass("hide");
		}
	});
});

jQuery.each(jQuery("textarea[data-autoresize]"), function () {
  var offset = this.offsetHeight - this.clientHeight;
  var resizeTextarea = function (el) {
    jQuery(el)
      .css("height", "auto")
      .css("height", el.scrollHeight + offset);
  };
  jQuery(this)
    .on("keyup input", function () {
      resizeTextarea(this);
    })
    .removeAttr("data-autoresize");
});
