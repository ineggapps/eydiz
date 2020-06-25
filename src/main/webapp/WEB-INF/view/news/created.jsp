<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

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
			<p style="height: 200px;"> 내용 : &nbsp;&nbsp;&nbsp;<textarea name="noticeContent" id="content" class="cinput" style="height: 170px; width: 700px;">${dto.noticeContent}</textarea> </p>
			<p> 파일 : &nbsp;&nbsp;&nbsp;<input class="cinput" type="file" name="upload"> </p>		        
	    </div>	
	    <div class="cbutton" style="text-align: center;">
			<button class="cbtn" type="button" onclick="sendOk('${mode}', '${pageNo}');">${mode=='update' ? '수정완료' : '등록하기'}</button> 
			<button class="cbtn" type="reset">다시입력</button>
			<button class="cbtn" type="button" onclick="sendCancel('${pageNo}');">${mode=='update' ? '수정취소' : '등록취소'}</button>       
	    	<c:if test="${mode=='update'}">
	    		<input type="hidden" name="noticeNo" value="${dto.noticeNo}">
	    		<input type="hidden" name="imageFilename" value="${dto.imageFilename}">
	    		<input type="hidden" name="page" value="${pageNo}">
	    	</c:if>
	    </div>
    </form>

</article>