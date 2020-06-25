<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function deleteMate() {
	var q = "fmNo=${dto.fmNo}";
	var url = "<%=cp%>/mate/delete?"+q;
	
	if(confirm("게시글을 삭제 하시겠습니까 ??"))
		location.href=url;
}

function updateMate() {
	var q = "fmNo=${dto.fmNo}&fmcaNo=${dto.fmcaNo}&pageNo=${pageNo}";
	
	var url = "<%=cp%>/mate/update?"+q;
	
	location.href=url;
}

function ajaxJSON(url, method, query, fn) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		dataType:"json",
		success:function(data) {
			fn(data);
		},
		beforeSend:function(jqXHR) {
			jqXHR.setRequestHeader("AJAX", true);
		},
		error:function(jqXHR) {
			if(jqXHR.state==403) {
				login();
				return false;
			}
			
			console.log(jqXHR.responseText);
		}
	});
}

$(function() {
	$(".btnSendMateProject").click(function() {
		if(! confirm("프로젝트에 참여 하시겠습니까?? ")) {
			return false;
		}
		
		var cnt = "${dto.mateProjectCount}";
		var tot = "${dto.fmAllNumber}";
		if(cnt>=tot) {
			alert("정원초과 되어 참여가 불가능 합니다...");
			return false;
		}
		var url = "<%=cp%>/mate/insertMateProject";
		var fmNo="${dto.fmNo}";
		var query = {fmNo:fmNo};
		
		var fn = function(data) {
			var state=data.state;
			if(state=="over") {
				alert("정원초과 되어 참여가 불가능 합니다...");
				var count = data.mateProjectCount
				$("#mateProjectCount").text(count);
			} else if(state=="true") {
				var count = data.mateProjectCount
				$("#mateProjectCount").text(count);
			} else if(state="false") {
				alert("프로젝트 참여는 한 아이디당 한 번만 가능 합니다...");
			}
		}
		
		ajaxJSON(url, "post", query, fn);
	});
});
</script>

<article class="row">
	<div class="mateTitle">
		<div class="matefirst">
			<p> <img src="<%=cp%>/uploads/mate/${dto.fmFilename}"> </p>
			<h2 style="margin-left: 100px;"> ${dto.memberNickname} </h2>
			<p> <span id="mateProjectCount">정원 : ( ${dto.mateProjectCount} / ${dto.fmAllNumber} )</span> </p>		
		</div>
		<div class="mnamec">
			<p> 기획부터 마케팅 까지 분야별 전문가들이 모여 기업에 딱 맞는 컨텐츠를 연구하고,<br> 꼭 필요한 종합 솔루션을 제공 합니다. </p>
		</div>
		<div class="matename">
			<p>${dto.fmSubject}</p>
		</div>
		<div class="matelistcontent">
			<p>${dto.fmContent} </p>
		</div>
		<div class="matecontentbtn">
			<p> <button class="mcb" type="button" onclick="updateMate();">수정</button> </p>
			<p> <button class="mcb" type="button" onclick="deleteMate();">삭제</button> </p>
			<p> <button class="mcb btnSendMateProject" type="button">참가하기</button> </p>
			<p> <button class="mcb" type="button" onclick="javascript:location.href='<%=cp%>/mate/main'">리스트</button> </p>
		</div>
	</div>
</article>