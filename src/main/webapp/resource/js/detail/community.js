//모달창 제어
const $overlay = $(".modalOverlay");
$(function(){
	$("#btnCommentModal").click(function(){
		$overlay.addClass("show");
	});
	$overlay.find(".btnClose").click(function(){
		$overlay.removeClass("show");
	})
})

//댓글쓰기
$(function(){
	const $modalForm = $("form[name=modalForm]");
	$overlay.find(".btnSubmit").click(function(){
		console.log($modalForm.serialize());
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
		$commentInputContent.removeClass("focus");
	});
	$commentInputContent.find("textarea").on("focusout",function(){
		const content = $(this).text();
		if(!content.trim()){
			$(this).css("height","32px");
		}
	});
	
});

jQuery.each(jQuery('textarea[data-autoresize]'), function() { 
	var offset = this.offsetHeight - this.clientHeight; var resizeTextarea = function(el) { jQuery(el).css('height', 'auto').css('height', el.scrollHeight + offset); }; jQuery(this).on('keyup input', function() { resizeTextarea(this); }).removeAttr('data-autoresize'); 
});

