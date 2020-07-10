<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function searchList() {
	var f=document.searchForm;
	f.submit();
}
</script>

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
 <div class="studio_nlist_text">
           <div class="titleBar">
             <div class="title"><h2>결제/후원 현황</h2></div>
			     <form name="searchForm" action="<%=cp%>/studio/project/${projectNo}/view/list" method="post" >
               		<div align="right">
	                    <select name="condition" style="border:none;">
	                      <option value="memberId" >아이디</option>
	                      <option value="buyNo" >구매번호</option>
	                    </select>
                   		<input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
                    	<button type="button" class="nl_searchButton" onclick="searchList()"><i class="fa fa-search" aria-hidden="true"></i></button>
                	</div>
				 </form>
              </div>


            <div class="studio_news_list" >
             <table class="studio_news_list_in">
             	<tr class="studio_news_list_oo">
             		<td class="studio_list_bname">구매번호</td>
             		<td class="studio_list_pname">아이디</td> 
					<td class="studio_list_bname">총 결제금액</td>
					<td class="studio_list_cname">결제일</td> 
					<td></td>
				</tr>
				<c:forEach var="dto" items="${list}" varStatus="status">
	             	<tr class="studio_news_list_o">
	             		<td class="studio_list_bname"> <a href="${readUrl}&newsNo=${dto.buyNo}">${dto.buyNo}</a></td>
						<td class="studio_list_pname">${dto.memberId}</td> 
						<td class="studio_list_bname">${dto.totalAmount}</td> 
						<td class="studio_list_sname">${dto.boughtDate}</td>
						
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