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
	
	$(function(){
		$("#adminProjectTab-${statusNo}").addClass("on");
		
		$("ul.adminProjectTabs li").click(function(){
			tab = $(this).attr("data-tab");
			
			$("ul.adminProjectTabs li").each(function(){
				$(this).removeClass("on");
			});
			
			$("#adminProjectTab-"+tab).addClass("on");
		});
	});
	
</script>

<article class="row">
  <h2>${parentCategoryNo == 1 ? '펀딩목록' : '기부목록'}</h2>
    <form name="adminProjectSearchForm" method="post">
      <div class="adminProjectSearchBox">
      	<select name="condition" class="adminProjectSearchSelect">
      		<option value="projectName" ${condition=="projectName"?"selected='selected'":""}>프로젝트명</option>
      		<option value="categoryName" ${condition=="categoryName"?"selected='selected'":""}>카테고리</option>
      		<option value="brandName" ${condition=="brandName"?"selected='selected'":""}>브랜드명</option>
      		<option value="created" ${condition=="created"?"selected='selected'":""}>등록일</option>
      	</select>
    	<input type="text" name="keyword" class="adminProjectSearch" placeholder="&nbsp;키워드를 입력하세요." value="${keyword}"><button type="button" class="adminProjectSearchBtn" onclick="searchProject()">검색</button>
      </div>
    </form>
  <div class="infoItemWrap">
	<nav class="row">
      <div class="rowFull adminProjectNav">
         <ul class="detailNav adminProjectTabs">
            <li id = "adminProjectTab-1" data-tab="1">
              <a href="<%=cp%>/admin/project/list?parentCategoryNo=${parentCategoryNo}&statusNo=1"><span>승인 대기</span></a>
            </li >
            <li id = "adminProjectTab-5" data-tab="5">
              <a href="<%=cp%>/admin/project/list?parentCategoryNo=${parentCategoryNo}&statusNo=5"><span>진행중</span></a>
            </li>
            <li id = "adminProjectTab-6" data-tab="6">
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
	    		<td>등록일</td>	    	
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