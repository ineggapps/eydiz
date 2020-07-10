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
 <div class="admin_list_text">
           <div class="titleBar">
             <div class="title"><h2>브랜드 목록</h2></div>
			     <form name="searchForm" action="<%=cp%>/admin/brand/list" method="post" >
               		<div align="right">
	                    <select name="condition">
	                      <option value="brandName" ${condition=="brandName"?"selected='selected'":""}>브랜드</option>
	                      <option value="managerName" ${condition=="managerName"?"selected='selected'":""}>관리자이름</option>
	                    </select>
                   		<input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
                    	<button type="button" class="l_searchButton" onclick="searchList()"><i class="fa fa-search" aria-hidden="true"></i></button>
                	</div>
				 </form>
              </div>

            <div class="admin_list" >
             <table class="admin_list_brand">
             	<tr class="admin_list_oo">
             		<td class="admin_list_bname"> 브랜드 이름 </td>
					<td class="admin_list_pname">관리자 이름</td> 
					<td class="admin_list_cname">관리자 이메일</td> 
					<td class="admin_list_sname">관리자 전화번호</td> 
				</tr>
				<c:forEach var="dto" items="${list}">
	             	<tr class="admin_list_o">
	             		<td class="admin_list_bname"> <a href="${readUrl}&brandNo=${dto.brandNo}"> ${dto.brandName} </a></td>
						<td class="admin_list_pname">${dto.managerName}</td> 
						<td class="admin_list_cname">${dto.managerEmail}</td> 
						<td class="admin_list_sname">${dto.managerPhone}</td> 
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