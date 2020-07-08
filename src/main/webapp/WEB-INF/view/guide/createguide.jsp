<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function sendOk() {
    var f = document.guideForm;

	var str = f.faqSubject.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.faqSubject.focus();
        return;
    }

	str = f.faqContent.value;
    if(!str) {
        alert("설명을 입력하세요. ");
        f.faqContent.focus();
        return;
    }
	
	f.action="<%=cp%>/guide/${mode}";

    f.submit();
}
</script>

<article class="row">
	<form name="guideForm" method="post" enctype="multipart/form-data">
		<div class="guidecreate">
	        	<ul>
	        		<li> <p>제목 : </p> </li>
	        		<li> <input type="text" name="faqSubject" value="${dto.faqSubject}"> </li>
	        	</ul>
	        	<ul>
	        		<li> <p>내용 : </p> </li>
	        		<li> <textarea name="faqContent">${dto.faqContent}</textarea> </li>
	        	</ul>
	        	<ul>
	        		<li> <p>파일 : </p> </li>
	        		<li> <input type="file" name="upload"> </li>
	        	</ul>
	        	<ul>
                 	<li> <p>아이콘 : </p> </li>
                    <li> <input type="text" name="faqIcon">${dto.faqIcon}</li>
              	</ul>
	        	<br><br><br>
	        	<ul>
	        		<li>
	        			<button type="button" onclick="sendOk();">${mode=='update' ? '수정완료' : '등록하기'}</button>
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
</article>