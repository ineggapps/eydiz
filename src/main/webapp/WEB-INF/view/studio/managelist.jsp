<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<script type="text/javascript">
function managesearchlist() {
	var f = document.managesearchForm;
	f.submit();
}
</script>

 <div class="studio_send_form">
           <div class="titleBar">
             <div class="title"><h2>배송 중</h2></div>
			     <form name="managesearchForm" action="" method="post" >
               		<div align="right" style="margin-bottom: 10px;">
	                    <select name="condition">
	                      <option value="buyNo" ${condition=="buyNo" ? "selected='selected'":""}>판매 번호</option>
	                      <option value="rewardTitle" ${condition=="rewardTitle" ? "selected='selected'":""}>리워드 제목</option>
	                    </select>
	                    <input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
	                    <input type="hidden" name="rows" value="${rows}">
	                    <button type="button" class="v_searchButton" onclick="managesearchlist()"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>
				 </form>
              </div>
            <div class="send_view" >
             <table class="send_view_info">
             	<tr class="send_view_o">
             		<td class="send_no">판매 번호</td>
					<td class="send_mNo">멤버 번호</td> 
					<td class="send_rsubject">구입 날짜</td> 
					<td class="send_sendStatus">배송상태</td> 
				</tr>
				<c:forEach var="dto" items="${managelist}">
	             	<tr class="send_view_t">
	             		<td class="send_no">${dto.buyNo}</td>
						<td class="send_mNo">${dto.memberNo}</td> 
						<%--${dto.projectNo가 아니라 projectNo를 넘겼어야지.. StudioProjectController.java  line 676: map.put("projectNo", projectNo); --%>
						<td class="send_rsubject"><a href="<%=cp%>/studio/sendContent/${projectNo}?buyNo=${dto.buyNo}&rewardNo=${dto.rewardNo}&page=${page}">${dto.boughtDate}</a></td> 
						<td class="send_sendStatus">${dto.statusName}</td> 
					</tr>
				</c:forEach>
			 </table>
		    </div>

           </div>
	 		<table style="width: 1100px; border-spacing: 0px;">
				<tr height="35">
					<td align="center">
						 ${managedataCount==0?"데이터가 없습니다.":paging}
					</td>
				</tr>
			</table>