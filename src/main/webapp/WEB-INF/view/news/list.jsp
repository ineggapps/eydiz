<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<c:forEach var="dto" items="${list}">
	<div class="borderBody">
		<div style="clear: both;">
			<div style="float: left; width: 490px;">
				<ul>
					<li>${dto.nocaName}</li>
					<li style="cursor: pointer;"><a onclick="javascript:contentView('${dto.noticeNo}', '${pageNo}')"> ${dto.noticeSubject} </a></li>
					<li>${dto.memberNickname}&nbsp;${dto.noticeCreated}</li>
				</ul>
			</div>
			<div style="float: left; width: 210px; padding-bottom: 30px;">
				<img alt="" src="<%=cp%>/uploads/news/${dto.imageFilename}" width="180" height="90">
			</div>
  		</div>
  	</div>
	</c:forEach>
  		<div class="boardfooter">
  			<ul>
				<li> ${dataCount==0 ? "등록된 게시물이 없습니다." : paging} </li>
			</ul> 
   		</div>
   		<div class="boardsearch">
   			<ul>
   				<li>
   				<form name="searchForm" method="post">
					<select name="condition" id="condition" style="margin-bottom: 40px;">
						<option value="all" ${condition=="all"?"selected='selected'":""}>전체</option>
						<option value="noticeSubject" ${condition=="noticeSubject"?"selected='selected'":""}>제목</option>
						<option value="noticeContent" ${condition=="noticeContent"?"selected='selected'":""}>내용</option>
						<option value="noticeCreated" ${condition=="noticeCreated"?"selected='selected'":""}>작성일</option>
					</select>
	 				<input type="text" name="keyword" id="keyword" value="${keyword}"> 
					<button type="button" onclick="searchList()">검색</button> 
				</form>
				</li>
			</ul>
			<div align="right" style="width: 150px; margin: 0px auto; padding-left: 600px;">
				<c:if test="${sessionScope.member.memberNo==1 || sessionScope.member.memberNo==dto.memberNo}">
					<button class="cbutton" type="button" onclick="insertForm();">등록하기</button>
				</c:if>
			</div>
		</div>