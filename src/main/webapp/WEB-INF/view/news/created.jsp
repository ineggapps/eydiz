<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript" src="<%=cp%>/resource/se/js/HuskyEZCreator.js" charset="utf-8"></script>

<article class="row">
	<form name="newsForm" method="post" enctype="multipart/form-data">
		<div class="bcreate">
			<h2 style="margin: 40px 0px 40px 500px; font-weight: bold; font-size: 24px;">| 등록하기</h2>
			<p> 제목 : &nbsp;&nbsp;&nbsp;<input name="noticeSubject" class="cinput" type="text" value="${dto.noticeSubject}"> </p>
			<p> 작성자 : &nbsp;&nbsp;&nbsp;${sessionScope.member.memberNickname}</p>
			<p> 카테고리: <select name="nocaNo">
							<option value="5" ${dto.nocaNo=="5" ? "selected='selected'":"" }>공지</option>
							<option value="6" ${dto.nocaNo=="6" ? "selected='selected'":"" }>이벤트</option>
							<option value="7" ${dto.nocaNo=="7" ? "selected='selected'":"" }>보도자료</option>
						</select>
			<p style="height: 270px;"> 내용 : &nbsp;&nbsp;&nbsp;<textarea name="noticeContent" id="content" class="cinput" style="height: 170px; width: 700px;">${dto.noticeContent}</textarea> </p>
			<p> 파일 : &nbsp;&nbsp;&nbsp;<input class="cinput" type="file" name="upload"> </p>		        
	    </div>	
	    <div class="cbutton" style="text-align: center;">
			<button class="cbtn" type="button" onclick="sendOk();">${mode=='update' ? '수정완료' : '등록하기'}</button> 
			<button class="cbtn" type="reset">다시입력</button>
			<button class="cbtn" type="button" onclick="sendCancel('${pageNo}');">${mode=='update' ? '수정취소' : '등록취소'}</button>      
	    	<c:if test="${mode=='update'}">
	    		<input type="hidden" name="noticeNo" value="${dto.noticeNo}">
	    		<input type="hidden" name="imageFilename" value="${dto.imageFilename}">
	    		<input type="hidden" name="page" value="${pageNo}">
	    	</c:if>
	    </div>
    </form>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "<%=cp%>/resource/se/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
		}
	}, //boolean
	fOnAppLoad : function(){
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["content"].getIR();
	alert(sHTML);
}

function sendOk() {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	
	
	var f = document.newsForm;
	
	var str = f.noticeSubject.value;
	if(!str) {
		alert("제목을 입력 하세요...");
		f.noticeSubject.focus();
		return;
	}
	
	var mode="${mode}";
    var url="<%=cp%>/news/"+mode;
    var query = new FormData(f); // IE는 10이상에서만 가능
    
	var fn = function(data){
		var state=data.state;
        if(state=="false") {
            alert("게시물을 추가(수정)하지 못했습니다. !!!");
            return false;
        }
            	
    	if(mode=="created") {
    		reloadNews()
    	} else {
    		var page = "${pageNo}";
    		listPage(page);
    	}
	};
	
	ajaxFileJSON(url, "post", query, fn);		
}

function setDefaultFont() {
	var sDefaultFont = '돋움';
	var nFontSize = 24;
	oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>

</article>