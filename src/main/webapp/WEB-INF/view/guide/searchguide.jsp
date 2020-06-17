<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
	<div class="contentWrapper">
        	<br>
        	<div class="searchtitle">
        		<p> 다음에 대한 검색 결과 : </p>
        	</div>
        	<br>
	        <div class="guidebox">
	        	<a href="#" class="boxcontent">
	        		<br><br>
	        		<span>제목</span><br><br>
	        		<span>내용</span><br><br>
	        		<span>작성자</span>
	        	</a>
	       	</div>
        </div>
</article>