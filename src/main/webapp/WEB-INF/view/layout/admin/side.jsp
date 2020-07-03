<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="titleInbox">
  <div class="titleInboxGradient"></div>
  <div class="titleInboxBackground"></div>
  <div class="titleInboxContent">
	  <p class="projectName">관리자 페이지</p>
  </div>
</div>
<div class="menuList">
  <ul class="subMenuItems">
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">브랜드 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">브랜드 목록</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">펀딩 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">펀딩 목록</a></li>
          <li class="subMenuItem"><a href="#">기부 목록</a></li> 
        </ul>
      </div>
    </li>
    <li class="extended">
      <div class="menuItem">
        <div class="menuName">
          <a href="#"  class="on">회원 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">일반 회원</a></li>
          <li class="subMenuItem"><a href="#">브랜드 회원</a></li>
        </ul>
      </div>
    </li>
  </ul>
</div>