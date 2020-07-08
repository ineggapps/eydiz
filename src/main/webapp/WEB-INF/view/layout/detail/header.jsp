<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<div class="headerInner">
  <h1 class="logo"><a href="<%=cp%>/main">eydiz</a></h1>
  <ul class="gnbExtended">
    <li> 
      <a href="#" class="menu on"><span>펀딩하기</span></a>
    </li>
    <li>
      <a href="#" class="menu"><span> 기부하기</span></a>
    </li>
  </ul>
  <div class="headerInnerRight">
    <ul class="gnbAuth">
      <li><a href="#" class="btnSubmit bordering btnMakeProject">프로젝트 만들기</a></li>
  	<c:if test="${empty sessionScope.member}">
      <li><a href="<%=cp%>/member/join">회원가입</a></li>
      <li><a href="<%=cp%>/member/login">로그인</a></li>
  	</c:if>
  	<c:if test="${not empty sessionScope.member}">
<li><a href="<%=cp%>/member/logout">로그아웃</a><li>
<li><a href="#" class="profilePic">${sessionScope.member.memberNickname}님</a></li>
  	</c:if>
  	</ul>
  </div>
</div>
<div class="headerInnerMobile">
  <div class="mobileNav">
    <h2 class="title">${project.projectName}</h2>
    <button type="button" class="btnBack btnFlat btnMobileNav">
      <span class="hidden">이전</span>
    </button>
    <button type="button" class="btnHome btnFlat btnMobileNav">
      <span class="hidden">홈</span>
    </button>
  </div>
</div>
<article class="row">
  <div class="rowFull">
    <div class="projectTitle">
      <div
        class="overlay"
        style="
          background-image: url('${project.projectImageUrl}');
        "
      ></div>
      <h3><span>${project.categoryName}</span></h3>
      <h2>${project.projectName}</h2>
    </div>
  </div>
</article>
<nav class="row">
  <div class="rowFull">
    <ul class="detailNav">
      <li ${uri=="/view"?"class=\"on\"":""} ${uri=="/"?"class=\"on\"":""}>
        <a href="<%=cp%>/detail/${projectNo}/view"><span>스토리</span></a>
      </li>
      <li  ${uri=="/news"?"class=\"on\"":""}>
        <a href="<%=cp%>/detail/${projectNo}/news"><span>새소식</span><span class="badge">${project.newsCount}</span></a>
      </li>
      <li  ${uri=="/community"?"class=\"on\"":""}>
        <a href="<%=cp%>/detail/${projectNo}/community"><span>커뮤니티</span><span class="badge">${project.communityCount}</span></a>
      </li>
    </ul>
  </div>
</nav>
