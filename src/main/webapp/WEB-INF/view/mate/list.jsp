<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<c:forEach var="dto" items="${list}">
		<div class="mateForm">
			<div class="matecontent" >
				<ul>
					<li class="mcontents">${dto.fmSubject}</li>
					<li class="mcontentc">${dto.fmContent}</li>
					<li style="cursor: pointer;" class="mcontent"><a onclick="javascript:contentView('${dto.fmNo}', '${pageNo}')"> 더 보기 > </a></li>
					<li class="mcount">총 정원 인원 : (${dto.fmAllNumber})명</li>
				</ul>
			</div>
		</div> 
</c:forEach>