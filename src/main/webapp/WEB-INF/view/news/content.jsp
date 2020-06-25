<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<article class="row">
	<div class="aForm">
      		<div class="atitle">
      			<h2> ${dto.noticeSubject} </h2>
      		</div>
      		<div class="apeople">
      			<h4> 작성자 :&nbsp;&nbsp;&nbsp; ${dto.memberNickname}</h4>
      		</div>
      		<div class="acontent">
      			<p>
					${dto.noticeContent}
      			</p>
      		</div>
      		<div class="contentpre">
      			<p>
      				이전글 :
      					<c:if test="${not empty preReadDto}">
      						<a href="javascript:contentView('${preReadDto.noticeNo}', '${pageNo}');">&nbsp; △ &nbsp; ${preReadDto.noticeSubject}</a>
      					</c:if>
      			</p>
      		</div>
      		<div class="contentnext">
      			<p>
      				다음글 :
      					<c:if test="${not empty nextReadDto}">
      						<a href="javascript:contentView('${nextReadDto.noticeNo}', '${pageNo}');">&nbsp; ▽ &nbsp; ${nextReadDto.noticeSubject}</a>
      					</c:if>
      			</p>
      		</div>
      	</div>
      	<div class="contentbutton">
      		<ul style="clear: both;">
      			<li style="float: left; width: 50%;">
      				<button type="button" onclick="updateForm('${dto.noticeNo}', '${pageNo}');">수정</button>
      				<button type="button" onclick="deleteNews('${dto.noticeNo}', '${pageNo}');">삭제</button>
      			</li>
      			<li style="float: left; width: 50%; text-align: right;">
      				<button type="button" onclick="listPage('${pageNo}');">리스트</button>
      			</li>
      		</ul>
      	</div>
</article>