<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<%--article class="row">
  <div class="rowFull">
    <h2>슬라이드</h2>
  </div>
</article--%>
<article class="row">
  <div class="rowInner">
    <h2 class="hidden">전체 분류</h2>
    <div class="categoryViewer">
    	<div class="scrollPanel scrollLeft">
    		<a href="#"><span class="hidden">이전</span></a>
    	</div>
    	<div class="scrollPanel scrollRight">
    		<a href="#"><span class="hidden">다음</span></a>
    	</div>
	    <ul class="categoryContent">
	    <c:forEach var="item" items="${fundingCategory}">
	      <li>
	        <div class="categoryItem">  
	          <a href="<%=cp %>/category/${item.categoryNo}"><span class="circle" style="background-image: url(${item.imageUrl})"></span><span class="text">${item.categoryName}</span></a>
	        </div>
	      </li>
	    </c:forEach>
	    </ul>
    </div>
  </div>
</article>
<article class="row">
  <div class="rowInner">
    <div class="titleBar">
      <div class="title"><h2>전체보기</h2></div>
      <div class="option">
        <form name="titleBarForm">
          <input
            type="text"
            name="keyword"
            class="keyword transparent"
            placeholder="검색"
          />
          <a href="#" class="searchButton"><span class="hidden">검색</span></a>
          <select name="sort1">
            <option value="1">전체</option>
            <option value="2">진행 중</option>
            <option value="3">종료된</option>
          </select>
          <select name="sort2">
            <option value="1">추천순</option>
            <option value="2">인기순</option>
            <option value="3">모금액순</option>
            <option value="4">마감 임박순</option>
            <option value="5">최신순</option>
            <option value="6">응원 참여자순</option>
          </select>
        </form>
      </div>
    </div>
    <ul class="gridContent">
      <c:forEach var="dto" items="${project}">
      <li class="item">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('${dto.projectImageUrl}');
            "
            onclick="javascript:location.href='<%=cp %>/detail/${dto.projectNo}'"
          >
            <span class="hidden">${dto.projectName}</span>
          </div>
          <div class="textWrap">
            <div class="subject">
              <a href="<%=cp %>/detail/${dto.projectNo}"
                ><span
                  >${dto.projectName}</span
                ></a
              >
            </div>
            <ul class="desc">
              <li><span class="category">${dto.categoryName}</span></li>
              <li><span class="name">${dto.brandName}</span></li>
            </ul>
            <div class="status">
              <div class="progress">
                <div class="progressBar" style="width: 100%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent">330%</span>&centerdot;<span class="totalAmount"
                    >${dto.projectGoalAmount}원</span
                  >
                </li>
                <li><span class="remainDays">9일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
      </c:forEach>
    </ul>
  </div>
  <script src="<%=cp%>/resource/js/main/main.js"></script>
</article>