<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<header id="header">
  <div class="headerInner">
    <h1 class="logo"><a href="#">wawawa</a></h1>
    <ul class="gnbExtended">
      <li>
        <a href="#" class="menu on"><span>펀딩하기</span></a>
      </li>
      <li>
        <a href="#" class="menu"><span>투자하기</span></a>
      </li>
      <li>
        <a href="#" class="menu"><span>스타트업 찾기</span></a>
      </li>
    </ul>
  </div>
  <div class="headerInnerMobile">
    <div class="headerMobile">
      <h1 class="logo"><a href="#">wawawa</a></h1>
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
      <li>
        <a href="#"><span>글로벌</span></a>
      </li>
    </ul>
  </div>
</header>