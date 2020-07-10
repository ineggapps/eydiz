<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function sendOk() {
    var f = document.schoolForm;

	var str = f.scSubject.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.scSubject.focus();
        return;
    }

	str = f.scContent.value;
    if(!str) {
        alert("설명을 입력하세요. ");
        f.scContent.focus();
        return;
    }
	
	f.action="<%=cp%>/school/${mode}";

    f.submit();
}
</script>

<article class="row">
	<form name="schoolForm" method="post" enctype="multipart/form-data">
		<div class="schoolcreate">
				<ul>
					<li class="createscca">
						<p> 카테고리 :  
							<select name="sccaNo">
								<option value="1" ${dto.sccaNo=="1" ? "selected='selected'":"" }>입문</option>
								<option value="2" ${dto.sccaNo=="2" ? "selected='selected'":"" }>실전</option>
								<option value="3" ${dto.sccaNo=="3" ? "selected='selected'":"" }>맨토링</option>
								<option value="4" ${dto.sccaNo=="4" ? "selected='selected'":"" }>특강</option>
							</select>
						</p>
					</li>
				</ul>
	        	<ul>
	        		<li class="createsub"> <p> 제목 :  <input type="text" name="scSubject" value="${dto.scSubject}"> </p> </li>
	        	</ul>
	        	<ul>
	        		<li class="createcon"> <p> 내용 :  <textarea name="scContent">${dto.scContent}</textarea> </p> </li>
	        	</ul>
	        	<ul>
	        		<li class="createfile"> <p> 파일 :  <input type="file" name="upload"> </p> </li>
	        	</ul>
	        	<ul>
	        		<li class="createDate"> <p> 강의 날짜 : <input type="text" name="scDate" value="${dto.scDate}"> </p> </li>
	        	</ul>
	        	<ul>
	        		<li class="createNumber"> <p> 정원인원 :  <input type="text" name="scAllNumber" value="${dto.scAllNumber}"> </p> </li>
	        	</ul>
	        	<br><br><br>
	        	<ul>
	        		<li class="createbutton">
	        			<button type="button" onclick="sendOk();">${mode=='update' ? '수정완료' : '등록하기'}</button>
	        			<button type="reset">다시입력</button>
	        			<button type="button" onclick="javascript:location.href='<%=cp%>/school/main'">${mode=='update' ? '수정취소' : '등록취소'}</button>
	        			<c:if test="${mode=='update'}">
	    					<input type="hidden" name="scNo" value="${dto.scNo}">
	    					<input type="hidden" name="scSavefilename" value="${dto.scSavefilename}">
	    				</c:if>
	        		</li>
	        	</ul>
		</div>
	</form>
</article>