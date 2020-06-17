<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
      <header class="guideheader">
	  	<div class="headerInner">
	        <br><br>
	        <h1 class="logo"><a href="#" style="color: white;">wawawa</a></h1>
     	</div>
     	<br><br>
     	<div>
     		<a class="textqna">
     			<input type="text" class="headerinput" value="" placeholder="답변 검색중...">
     		</a>
     		<button type="button" class="img-button"><img width="40" height="60" alt="" src="resource/images/search.png"> </button>
     	</div>
      </header>