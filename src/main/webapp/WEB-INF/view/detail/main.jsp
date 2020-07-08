<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="detailSummary">
<p>
	<img class="fullWidth" src="${project.projectImageUrl}" alt="${project.projectName}" />
</p>
<p>
	${project.projectSummary}
</p>
</div>
<hr class="divider deeper" />
<div class="detailStory">
	${project.projectStory}
</div>
