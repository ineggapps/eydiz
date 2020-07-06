//모달창 제어
const $overlay = $(".modalOverlay");
function openModal(){
	$overlay.addClass("show");
}
function closeModal(){
	$overlay.removeClass("show");
}
$(function(){
	$("#btnCommentModal").click(function(){
		openModal();
	});
	$overlay.find(".btnClose").click(function(){
		closeModal();
	})
})

//댓글 불러오기
$(function(){
	const url = cp + "/detail/" + projectNo + "/community/view";
	const method = "post";
	const query = "projectNo="+projectNo;
	const $wrap = $(".commentWrap");
	const $target= $(".parent.commentItem");
	ajaxJSON(url, method, query).then(function(data){
		var $element; 
		const comments = data.comments;
		$.each(comments, function(idx, item){
			$element = $target.clone().appendTo($wrap).removeClass("hide");
			console.log($element);
			$element.find("span.parent.author").eq(0).text(item.memberId);
			$element.find("span.parent.date").eq(0).text(item.createdDate);
			$element.find("div.parent.commentContent").eq(0).text(item.content);
			
			//답글 개수 등...
		});
	}).catch(function(e){
		console.log(e);
	});
});

//댓글쓰기
$(function(){
	const $modalForm = $("form[name=modalForm]");
	$overlay.find(".btnSubmit").click(function(){
		query = $modalForm.serialize();
		const url = cp + "/detail/" + projectNo + "/community/create";
		console.log(query);
		ajaxJSON(url, "post", query).then(function(data){
			if(data.result=="ok"){
				alert("댓글을 작성했습니다.");
				//TODO: 댓글 새로 불러오기 명령어 호출하기
				closeModal();
			}
		}).catch(function(e){
			console.log(e);
		});
	});
});

//답글 쓰기
$(function(){
	//focus, blur
	const $commentInputContent =$(".commentInputContent");
	$commentInputContent.on("click focus", function(){
		$(this).addClass("focus");
	});
	$commentInputContent.on("focusout", function(){
		const content = $(this).find("textarea").val();
		if(!content.trim()){
			$commentInputContent.removeClass("focus");
			$(this).css("height","32px");
		}
	});
	
});

jQuery.each(jQuery('textarea[data-autoresize]'), function() { 
	var offset = this.offsetHeight - this.clientHeight; var resizeTextarea = function(el) { jQuery(el).css('height', 'auto').css('height', el.scrollHeight + offset); }; jQuery(this).on('keyup input', function() { resizeTextarea(this); }).removeAttr('data-autoresize'); 
});

