<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
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
	        <h1 class="logo"><a href="#" style="color: white;">eydiz
	        </a></h1>
     	</div>
     	<br><br>
     	<div>
     		<a class="textqna">
     			<input type="text" class="headerinput" value="${keyword}" name="keyword" placeholder="답변 검색중...">
     		</a>
     		<button type="button" class="btnGuideInfoSearch  img-button" onclick="search();"></button>
     	</div>
      </header>
</form>