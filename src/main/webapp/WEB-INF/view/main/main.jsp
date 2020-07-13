<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
      <div class="title"><h2>${empty categoryName?"전체 보기":categoryName}</h2></div>
      <div class="option">
        <form name="titleBarForm">
          <input
            type="text"
            name="keyword"
            id="keyword"
            class="keyword transparent"
            placeholder="검색"
          />
          <a href="#" class="searchButton"><span class="hidden">검색</span></a>
          <select name="status" id="status">
            <option value="all">전체</option>
            <option value="ongoing">진행 중</option>
            <option value="closed">종료된</option>
          </select>
          <select name="sort" id="sort">
            <option value="recommend">추천순</option>
            <option value="popular">인기순</option>
            <option value="amount">모금액순</option>
            <option value="deadline">마감 임박순</option>
            <option value="latest">최신순</option>
            <option value="community">응원 참여자순</option>
          </select>
        </form>
      </div>
    </div>
    <ul class="gridContent"></ul>
    <ul class="gridContent sample">
      <li class="item sample">
        <div class="itemInner">
          <div
            class="thumbnail"
            style="
              background-image: url('${dto.projectImageUrl}');
            "
            onclick="goToLocation(this)";
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
                <div class="progressBar" style="width: ${dto.attainRate*100}%;"></div>
              </div>
              <ul>
                <li>
                  <span class="percent"><fmt:formatNumber type = "number" pattern = "###.##" value = "${dto.attainRate*100}" />%</span>&centerdot;<span class="totalAmount"
                    ><fmt:formatNumber type = "number" pattern = "#,###" value = "${dto.totalAmount}" />원</span
                  >
                </li>
                <li><span class="remainDays">${dto.remainDays}일 남음</span></li>
              </ul>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <script src="<%=cp%>/resource/js/main/main.js"></script>
</article>