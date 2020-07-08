<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<script type="text/javascript">
function search() {
	var f = document.searchForm;
	f.submit();
}
</script>

<form name="searchForm" method="post" action="<%=cp%>/guide/main">
      <header class="guideheader">
	  	<div class="headerInner">
	        <br><br>
	        <p class="qlogo"><a href="#"> eydiz </a></p>
     	</div>
     	<br><br>
     	<div class="textqna">
     		<input type="text" class="headerinput" value="${keyword}" name="keyword" placeholder="답변 검색중...">
     		<button type="button" class="btnGuideInfoSearch  img-button" onclick="search();"><i class="fas fa-search fa-1x"></i></button>
     	</div>
      </header>
</form>