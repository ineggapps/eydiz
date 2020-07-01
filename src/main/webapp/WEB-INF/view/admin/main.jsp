<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  	<h3 class="membertitle">일반 회원 목록</h3>
 	<div class="memberlist">
 		<ul>
 			<li>회원 번호</li>
 			<li>회원 아이디</li>
 			<li>회원 닉네임</li>
 			<li>회원 이메일</li>
 		</ul>
 		<c:forEach var="" items="${list}">
	 		<ul style="clear:both;">
	 			<li>1</li>
	 			<li>1</li>
	 			<li>1</li>
	 			<li>1</li>
	 		</ul>
 		</c:forEach>
 	</div>
</article>