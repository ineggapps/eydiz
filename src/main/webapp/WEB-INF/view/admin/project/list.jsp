<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<article class="row">
  <h2>펀딩 목록</h2>
    <form name="adminProjectSearchForm" method="post">
      <div class="adminProjectSearchBox">
      	<select name="condition" class="adminProjectSearchSelect">
      		<option value="projectName" ${condition=="projectName"?"selected='selected'":""}>프로젝트명</option>
      		<option value="categoryName" ${condition=="categoryName"?"selected='selected'":""}>카테고리</option>
      		<option value="brandName" ${condition=="brandName"?"selected='selected'":""}>브랜드명</option>
      	</select>
    	<input type="text" name="keyword" class="adminProjectSearch" placeholder="&nbsp;키워드를 입력하세요." value="${keyword}"><button type="button" class="adminProjectSearchBtn">검색</button>
      </div>
    </form>
  <div class="infoItemWrap">
	<nav class="row">
      <div class="rowFull adminProjectNav">
         <ul class="detailNav">
            <li class="on">
              <a href="#"><span>승인 대기</span></a>
            </li>
            <li>
              <a href="#"><span>진행중</span></a>
            </li>
            <li>
              <a href="#"><span>종료</span></a>
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
	    	</tr>
	    <c:forEach var="dto" items="${list}">
	    	<tr>
	    		<td>${dto.rowNum}</td>
	    		<td>${dto.categoryName}</td>
	    		<td>${dto.projectName}</td>
	    		<td>${dto.brandName}</td>
	    		<td>${dto.managerName}</td>
	    	</tr>
	    </c:forEach>
	    </table>
	    <div class="adminProjectPaging">
	    	${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
	    </div>
	</div>
  </div>
</article>