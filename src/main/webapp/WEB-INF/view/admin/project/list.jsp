<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
	function searchProject() {
		var f = document.adminProjectSearchForm;
		f.submit();
	}
</script>

<article class="row">
  <h2>${parentCategoryNo == 1 ? '펀딩목록' : '기부목록'}</h2>
    <form name="adminProjectSearchForm" method="post">
      <div class="adminProjectSearchBox">
      	<select name="condition" class="adminProjectSearchSelect">
      		<option value="projectName" ${condition=="projectName"?"selected='selected'":""}>프로젝트명</option>
      		<option value="categoryName" ${condition=="categoryName"?"selected='selected'":""}>카테고리</option>
      		<option value="brandName" ${condition=="brandName"?"selected='selected'":""}>브랜드명</option>
      		<option value="created" ${condition=="created"?"selected='selected'":""}>접수일</option>
      	</select>
    	<input type="text" name="keyword" class="adminProjectSearch" placeholder="&nbsp;키워드를 입력하세요." value="${keyword}"><button type="button" class="adminProjectSearchBtn">검색</button>
      </div>
    </form>
  <div class="infoItemWrap">
	<nav class="row">
      <div class="rowFull adminProjectNav">
         <ul class="detailNav">
            <li class="on">
              <a href="<%=cp%>/admin/project/list?parentCategoryNo=${parentCategoryNo}&statusNo=1"><span>승인 대기</span></a>
            </li>
            <li>
              <a href="<%=cp%>/admin/project/list?parentCategoryNo=${parentCategoryNo}&statusNo=5"><span>진행중</span></a>
            </li>
            <li>
              <a href="<%=cp%>/admin/project/list?parentCategoryNo=${parentCategoryNo}&statusNo=6"><span>종료</span></a>
            </li>
         </ul>
      </div>
    </nav>
    <div class="adminProjectContentBox">
	    <table class="adminProjectListTable">
	    	<tr>
	    		<td>No</td>
	    		<td>카테고리</td>
	    		<td>프로젝트명</td>
	    		<td>브랜드명</td>
	    		<td>프로젝트 담당자</td>
	    		<td>접수일</td>	    	
	    	</tr>
	    <c:forEach var="dto" items="${list}">
	    	<tr onclick="location.href='${articleUrl}&projectNo=${dto.projectNo}'"
	    		style="cursor: pointer;">
	    		<td>${dto.rowNum}</td>
	    		<td>${dto.categoryName}</td>
	    		<td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
	    			${dto.projectName}
	    		</td>
	    		<td>${dto.brandName}</td>
	    		<td>${dto.managerName}</td>
	    		<td>${dto.created}</td>
	    	</tr>
	    </c:forEach>
	    </table>
	    <div class="adminProjectPaging">
	    	${dataCount == 0 ? "등록된 프로젝트가 존재하지 않습니다." : paging}
	    </div>
	</div>
  </div>
</article>