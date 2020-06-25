<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function sendOk() {
    var f = document.mateForm;

	var str = f.fmSubject.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.fmSubject.focus();
        return;
    }

	str = f.fmContent.value;
    if(!str) {
        alert("설명을 입력하세요. ");
        f.fmContent.focus();
        return;
    }
	
	f.action="<%=cp%>/mate/${mode}";

    f.submit();
}
</script>

<article class="row">
	<form name="mateForm" method="post" enctype="multipart/form-data">
		<div class="bcreate">
			<h2 style="margin: 40px 0px 40px 500px; font-weight: bold; font-size: 24px;">| 등록하기</h2>
			<p> 제목 : &nbsp;&nbsp;&nbsp;<input name="fmSubject" class="cinput" type="text" value="${dto.fmSubject}"> </p>
			<p> 작성자 : &nbsp;&nbsp;&nbsp;${sessionScope.member.memberNickname}</p>
			<p> 카테고리: <select name="fmcaNo">
							<option value="2" ${dto.fmcaNo=="2" ? "selected='selected'":"" }>펀딩/물품</option>
							<option value="3" ${dto.fmcaNo=="3" ? "selected='selected'":"" }>펀딩/공연</option>
							<option value="4" ${dto.fmcaNo=="4" ? "selected='selected'":"" }>펀딩/음악</option>
							<option value="5" ${dto.fmcaNo=="5" ? "selected='selected'":"" }>기부</option>
						</select>
			<p style="height: 200px;"> 내용 : &nbsp;&nbsp;&nbsp;<textarea name="fmContent" id="content" class="cinput" style="height: 170px; width: 700px;">${dto.fmContent}</textarea> </p>
			<p> 파일 : &nbsp;&nbsp;&nbsp;<input class="cinput" type="file" name="upload"> </p>	
			<p> 정원 : <input type="text" class="cinput" name="fmAllNumber" value="${dto.fmAllNumber}"> </p>	        
	    </div>	
	    <div class="cbutton" style="text-align: center;">
			<button class="cbtn" type="button" onclick="sendOk();">${mode=='update' ? '수정완료' : '등록하기'}</button> 
			<button class="cbtn" type="reset">다시입력</button>
			<button class="cbtn" type="button" onclick="javascript:location.href='<%=cp%>/mate/main'">${mode=='update' ? '수정취소' : '등록취소'}</button>       
	    	<c:if test="${mode=='update'}">
	    		<input type="hidden" name="fmNo" value="${dto.fmNo}">
	    		<input type="hidden" name="fmFilename" value="${dto.fmFilename}">
	    		<input type="hidden" name="pageNo" value="${pageNo}">
	    	</c:if>
	    </div>
    </form>

</article>