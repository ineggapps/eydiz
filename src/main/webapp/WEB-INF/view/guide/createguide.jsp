<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript" src="<%=cp%>/resource/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function sendOk() {
    var f = document.guideForm;

	var str = f.faqSubject.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.faqSubject.focus();
        return false;
    }

	str = f.faqContent.value;
    if(!str || str=="<p>&nbsp;</p>") {
        alert("설명을 입력하세요. ");
        f.faqContent.focus();
        return false;
    }
	
	f.action="<%=cp%>/guide/${mode}";
	return true;
}
</script>

	<form name="guideForm" method="post" enctype="multipart/form-data" onsubmit="return submitContents(this)">
		<div class="guidecreate">
	        	<ul class="guideborder">
	        		<li class="guidesname"> <p> 제목 : </p> </li>
	        		<li class="guideInput"> <input type="text" name="faqSubject" value="${dto.faqSubject}"> </li>
	        	</ul>
	        	<ul class="guideborder">
	        		<li class="guidesname"> <p>내용 : </p> </li>
	        		<li class="guideInput"> <textarea name="faqContent" id="faqContent">${dto.faqContent}</textarea> </li>
	        	</ul>
	        	<ul class="guideborder">
	        		<li class="guidesname"> <p>파일 : </p> </li>
	        		<li class="guideInput"> <input type="file" name="upload"> </li>
	        	</ul>
	        	<ul class="guideborder">
                 	<li class="guidesname"> <p>아이콘 : </p> </li>
                    <li class="guideInput"> <input type="text" name="faqIcon">${dto.faqIcon}</li>
              	</ul>
	        	
	        	<br><br><br>
	        	<ul>
	        		<li class="guidebottombutton">
	        			<button type="submit">${mode=='update' ? '수정완료' : '등록하기'}</button>
	        			<button type="reset">다시입력</button>
	        			<button type="button" onclick="javascript:location.href='<%=cp%>/guide/main'">${mode=='update' ? '수정취소' : '등록취소'}</button>
	        			<c:if test="${mode=='update'}">
	    					<input type="hidden" name="faqNo" value="${dto.faqNo}">
	    					<input type="hidden" name="faqFilename" value="${dto.faqFilename}">
	    				</c:if>
	        		</li>
	        	</ul>
		</div>
	</form>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "faqContent",
	sSkinURI: "<%=cp%>/resource/se/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["faqContent"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["faqContent"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["faqContent"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
	
	try {
		// elClickedObj.form.submit();
		return check();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '돋움';
	var nFontSize = 24;
	oEditors.getById["faqContent"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>    