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

 <div class="admin_bmember_form">
           <div class="titleBar">
             <div class="title"><h2>브랜드 목록</h2></div>
			     <form name="membersearchForm" action="" method="post" >
               <div align="right" style="margin-bottom: 10px;">
                    <select name="condition">
                      <option value="memberNo" ${condition=="memberNo" ? "selected='selected'":""}>회원 번호</option>
                      <option value="brandName" ${condition=="brandName" ? "selected='selected'":""}>브랜드 이름</option>
                    </select>
                   <input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
                   <input type="hidden" name="rows" value="${rows}">
                    <button type="button" class="v_searchButton" onclick="membersearchlist()"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>
				 </form>
              </div>
              

            <div class="bmemeber_view" >
             <table class="bmember_view_member">
             	<tr class="bmember_view_o">
             		<td class="bmember_no">회원번호</td>
					<td class="bmember_name">브랜드 이름</td> 
					<td class="bmember_mname">프로젝트 매니저 이름</td> 
					<td class="bmember_memail">프로젝트 매니저 이메일</td> 
				</tr>
				<c:forEach var="dto" items="${list}">
	             	<tr class="bmember_view_t">
	             		<td class="bmember_no">${dto.memberNo}</td>
						<td class="bmember_name">${dto.brandName}</td> 
						<td class="bmember_mname">${dto.managerName}</td> 
						<td class="bmember_memail">${dto.managerEmail}</td> 
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