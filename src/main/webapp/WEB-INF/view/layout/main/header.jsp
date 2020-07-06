<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<header id="header">
  <div class="headerInner">
    <h1 class="logo"><a href="<%=cp%>">eydiz</a></h1>
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
        <li><a href="<%=cp %>/studio/project/list" class="btnSubmit bordering btnMakeProject">프로젝트 관리</a></li>
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
    <div class="headerMobile">
      <h1 class="logo"><a href="#">eydiz</a></h1>
    </div>
  </div>
  <div class="headerInnerTab">
    <ul class="gnbSub">
      <li>
        <a href="#" class="on"><span>펀딩 홈</span></a>
      </li>
      <li class="trivial">
        <a href="#"><span>카테고리</span></a>
      </li>
      <li>
        <a href="#"><span>오픈예정</span></a>
      </li>
    </ul>
  </div>
</header>