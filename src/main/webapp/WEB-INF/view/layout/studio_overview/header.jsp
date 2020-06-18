<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<header id="header">
  <div class="headerInner">
    <h1 class="logo"><a href="#">wawawa</a></h1>
    <ul class="gnbExtended">
      <li>
        <a href="#" class="menu"><span>펀딩하기</span></a>
      </li>
      <li>
        <a href="#" class="menu"><span>기부하기</span></a>
      </li>
    </ul>
  </div>
  <div class="headerInnerMobile">
    <div class="mobileNav">
      <h2 class="title">이디즈 - 브랜드 &centerdot; 프로젝트 관리</h2>
      <a href="#" class="btnBack btnFlat btnMobileNav">
        <span class="hidden">이전</span>
      </a>
      <a href="#" class="btnHome btnFlat btnMobileNav">
        <span class="hidden">홈</span>
      </a>
    </div>
  </div>
</header>