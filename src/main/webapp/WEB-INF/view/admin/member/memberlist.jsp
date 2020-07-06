<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<script type="text/javascript">
function membersearchlist() {
	var f = document.membersearchForm;
	f.submit();
}
</script>


<div class="admin_member_form">
           <div class="member_title">
           		<h2>회원 목록</h2>
			     <form name="membersearchForm" action="<%=cp%>/admin/member/memberlist" method="post" >
	               <div align="right">
	                    <select name="condition">
	                      <option value="memberNo" ${condition=="memberNo" ? "selected='selected'":""}>회원 번호</option>
	                      <option value="memberId" ${condition=="memberId" ? "selected='selected'":""}>회원 아이디</option>
	                    </select>
	                   <input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
	                   <input type="hidden" name="rows" value="${rows}">
	                    <button type="button" class="v_searchButton" onclick="membersearchlist()"><i class="fa fa-search" aria-hidden="true"></i></button>
	                </div>
				 </form>
              </div>

            <div class="member_view" >
             <table class="member_view_member">
             	<tr class="member_view_o">
             		<td class="member_no">회원 번호</td>
					<td class="member_id">회원 아이디</td> 
					<td class="member_name">회원 닉네임</td> 
					<td class="member_email">회원 이메일</td>
				</tr>
				<c:forEach var="dto" items="${list}">
	             	<tr class="member_view_t">
	             		<td class="member_no">${dto.memberNo}</td>
						<td class="member_id">${dto.memberId}</td> 
						<td class="member_name">${dto.memberNickname}</td> 
						<td class="member_email">${dto.memberEmail}</td> 
					</tr>
				</c:forEach> 
			 </table>
		    </div>
           </div>
           
 		<table style="width: 1100px; border-spacing: 0px;">
			<tr height="35">
				<td align="center">
					 ${dataCount==0?"데이터가 없습니다.":paging}
				</td>
			</tr>
		</table>