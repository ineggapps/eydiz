<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">

<table style='width: 100%; margin: 10px auto 30px; border-spacing: 0px;'>
	<thead id='listCommentHeader'>
		<tr height='35'>
		    <td colspan='2'>
		       <div style='clear: both;'>
		           <div style='float: left;'><span style='color: #3EA9CD; font-weight: bold;'>댓글 ${commentCount}개</span></div>
		           <div style='float: right; text-align: right;'></div>
		       </div>
		    </td>
		</tr>
	</thead>
	
	<tbody id='listCommentBody'>
	<c:forEach var="dto" items="${listComment}">
	    <tr style="border-top: 1px solid #eeeeee;">
	       <td height="30px;" style="border: none; padding: 10px; vertical-align: baseline;">
	         <i class="far fa-user-circle fa-1x" style="font-size: 30px;"></i><span style="position:relative;top:-5px;">&nbsp;&nbsp;${dto.memberId}</span>         
	       </td>
	    </tr>
	    <tr>
	       <td  style="border: none; padding: 10px;">
	             ${dto.commentContent}
	       </td>
	    </tr>
	    <tr>
	       <td height="20px;" style="border-bottom: 1px solid #eeeeee; padding: 10px; color: #A4A4A4">
	           <span>${dto.commentCreated}</span> |
	           <c:if test="${dto.memberId == sessionScope.member.memberId ||  sessionScope.member.memberId == 'eydiz' }">
	                <span class="deleteComment" style="cursor: pointer; color: #6E6E6E " data-commentNum='${dto.commentNum}' data-pageNo='${pageNo}'>삭제</span>
	           </c:if>
	        </td>
	    </tr>
	</c:forEach>
	</tbody>
	
	<tfoot id='listCommentFooter'>
		<tr height='40' align="center">
            <td colspan='2' >
              ${paging}
            </td>
           </tr>
	</tfoot>
</table>
