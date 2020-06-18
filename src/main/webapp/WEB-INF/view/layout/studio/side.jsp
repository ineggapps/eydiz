<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="titleInbox">
  <p class="projectName">브랜드명의<br />멋진 프로젝트</p>
  <p class="info">프로젝트 번호: 00000</p>
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
          <li class="subMenuItem"><a href="#">브랜드 정보 수정</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#" class="on">펀딩/후원 준비</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#" class="on">기본 정보</a></li>
          <li class="subMenuItem"><a href="#">리워드 설계</a></li>
          <li class="subMenuItem"><a href="#">스토리 작성</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">새소식 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">새소식 목록</a></li>
          <li class="subMenuItem"><a href="#">새소식 등록</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">펀딩/후원 현황</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">결제/후원 현황</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">발송 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">배송현황 조회</a></li>
        </ul>
      </div>
    </li>
    <li>
      <div class="menuItem">
        <div class="menuName">
          <a href="#">문의사항 관리</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="#">문의현황 조회</a></li>
        </ul>
      </div>
    </li>
  </ul>
</div>