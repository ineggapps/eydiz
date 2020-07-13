<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="titleInbox">
  <div class="titleInboxGradient"></div>
  <div class="titleInboxBackground" style="background-image:url(${project.projectImageUrl})"></div>
  <div class="titleInboxContent">
	  <p class="projectName">${not empty project.projectName?project.projectName:"제목 없음"}의<br />관리 페이지</p>
	  <p class="info">프로젝트 번호: <fmt:formatNumber pattern="00000" value="${projectNo}"/></p>
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
          <li class="subMenuItem"><a href="<%=cp%>/studio/brand/info">브랜드 관리</a></li>
        </ul>
      </div>
    </li>
    <li class="extended">
      <div class="menuItem">
        <div class="menuName">
          <a href="#" class="on">펀딩/후원 준비</a>
          <a href="#" class="arrow"><span class="hidden">열기/닫기</span></a>
        </div>
        <ul class="subMenu">
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/register" class="on">기본 정보</a></li>
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/reward">리워드 설계</a></li>
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/story">스토리 작성</a></li>
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
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/news/list">새소식 목록</a></li>
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/news/write">새소식 등록</a></li>
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
          <li class="subMenuItem"><a href="<%=cp%>/studio/project/${projectNo}/view/list">결제/후원 현황</a></li>
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