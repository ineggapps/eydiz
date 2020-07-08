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
             <div class="title"><h2>새소식 목록</h2></div>
			     <form name="searchForm" action="<%=cp%>/studio/project/${projectNo}/news/list" method="post" >
               		<div align="right">
	                    <select name="condition">
	                      <option value="title" >새소식 제목</option>
	                      <option value="createdDate" >작성일자</option>
	                    </select>
                   		<input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
                    	<button type="button" class="nl_searchButton" onclick="searchList()"><i class="fa fa-search" aria-hidden="true"></i></button>
                	</div>
				 </form>
              </div>


            <div class="studio_news_list" >
             <table class="studio_news_list_in">
             	<tr class="studio_news_list_oo">
             		<td class="studio_list_bname">새소식 번호</td>
             		<td class="studio_list_pname">새소식 제목</td> 
					<td class="studio_list_pname">프로젝트 번호</td>
					<td class="studio_list_cname">작성일자</td> 
				</tr>
				<c:forEach var="dto" items="${list}">
	             	<tr class="studio_news_list_o">
	             		<td class="studio_list_bname"> <a href="${readUrl}&newsNo=${dto.newsNo}"> ${dto.newsNo} </a></td>
						<td class="studio_list_pname">${dto.title}</td> 
						<td class="studio_list_cname">${dto.projectNo}</td> 
						<td class="studio_list_sname">${dto.createdDate}</td> 
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