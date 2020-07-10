<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="newsWrap">
	<hr class="divider deeper sample" />
	<div class="newsItem sample">
		<p class="number">#1</p>
		<p class="newsTitle">제목</p>
		<div class="newsContent">내용...</div>
	</div>
</div>
<div class="newsMore">
	<button type="button" class="btnNewsMore">더 보기<span class="bgMore">&nbsp;</span></button>
</div>
<script src="<%=cp%>/resource/js/detail/news.js"></script>