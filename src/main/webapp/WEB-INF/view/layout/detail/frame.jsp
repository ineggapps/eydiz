<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>크라우드 펀딩</title>
    <link rel="stylesheet" href="<%=cp%>/resource/css/reset.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/layout.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/detail/reward.css" />
    <!--[if lt IE 9]>
      <script src="resource/js//html5shiv.min.js"></script>
    <![endif]-->
    <script>
    const cp = "<%=cp%>";
    const projectNo = ${project.projectNo};
    </script>
    <script src="<%=cp %>/resource/js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript" src="<%=cp %>/resource/js/lib/bluebird.js"></script>    
	<script type="text/javascript" src="<%=cp %>/resource/js/detail/detail.js"></script>    
  </head>
  <body>
    <div id="wrap">
      <header id="header">
        <tiles:insertAttribute name="header" />
      </header>
      <main id="content">
        <div class="contentWrapper">
			<tiles:insertAttribute name="content"/>
        </div>
      </main>
      <footer id="footer">
        <tiles:insertAttribute name="footer" />
      </footer>
    </div>
  </body>
</html>
