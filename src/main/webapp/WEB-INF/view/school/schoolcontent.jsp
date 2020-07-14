<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function deleteSchool() {
	var q = "scNo=${dto.scNo}";
	var url = "<%=cp%>/school/delete?"+q;
	
	if(confirm("게시글을 삭제 하시겠습니까 ??"))
		location.href=url;
}

function updateSchool() {
	var q = "scNo=${dto.scNo}";
	
	var url = "<%=cp%>/school/update?"+q;
	
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
	$(".btnSendSchoolProject").click(function() {
		if(! confirm("프로젝트에 참여 하시겠습니까?? ")) {
			return false;
		}
		
		var cnt = "${dto.schoolProject}";
		var tot = "${dto.scAllNumber}";
		if(cnt>=tot) {
			alert("정원초과 되어 참여가 불가능 합니다...");
			return false;
		}
		var url = "<%=cp%>/school/insertSchoolProject";
		var scNo="${dto.scNo}";
		var query = {scNo:scNo};
		
		var fn = function(data) {
			var state=data.state;
			if(state=="over") {
				alert("정원초과 되어 참여가 불가능 합니다...");
				var count = data.schoolProject
				$("#schoolProject").text(count);
			} else if(state=="true") {
				var count = data.schoolProject
				$("#schoolProject").text(count);
			} else if(state="false") {
				alert("프로젝트 참여는 한 아이디당 한 번만 가능 합니다...");
			}
		}
		
		ajaxJSON(url, "post", query, fn);
	});
});
</script>

<div class="schoolAll">
	<div class="schoolarticle">
		<div class="sContentTitle">
			<h2>[${dto.sccaName}] ${dto.scSubject}</h2>
			<p> ${dto.scContent} </p>
		</div>
		<div class="titlesbtn">
			<button class="titlebtn" type="button" onclick="javascript:location.href='<%=cp%>/school/main'">리스트</button>
		</div>
		<div class="sContentl">
			<ul>
				<li> ${dto.scSubject} </li>
			</ul>
		</div>
		<div class="sContentr">
			<ul>
				<li class="topcontent">강사 닉네임</li>
				<li class="bottomcontent">${dto.memberNickname}</li>
			</ul>
			<ul>
				<li class="topcontent">강의 날짜</li>
				<li class="bottomcontent">${dto.scDate}</li>
			</ul>
			<ul>
				<li class="topcontent">모집/제한 인원 </li>
				<li class="bottomcontent"> <span id="schoolProject"> ( ${dto.schoolProject} / ${dto.scAllNumber} )</span> </li>
			</ul>
			<ul>
				<li class="projectbutton"><a class="schbtn btnSendSchoolProject">참가하기</a> </li>
			</ul>
		</div>
		<c:if test="${sessionScope.member.memberNo==1 || sessionScope.member.memberNo==dto.memberNo}">
			<div class="contentbutton">
					<p> <button style="margin-right: 10px;" class="mcb" type="button" onclick="updateSchool();">수정</button> </p>
					<p> <button class="mcb" type="button" onclick="deleteSchool();">삭제</button> </p>
			</div>
		</c:if>
	</div>
</div>
