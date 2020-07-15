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
		        <a href="<%=cp %>/main" class="menu"><span>펀딩하기</span></a>
		      </li>
		      <li>
		        <a href="<%=cp %>/awards/list" class="menu"><span>어워즈</span></a>
		      </li>
		      <li>
		        <a href="<%=cp %>/partner/list" class="menu on"><span>파트너</span></a>
		      </li>
		    </ul>
          <div class="headerInnerRight">
            <ul class="gnbAuth">
	      	<c:if test="${not empty sessionScope.member }">
	        <li><a href="<%=cp %>/studio/project/list" class="btnSubmit bordering btnMakeProject">프로젝트 관리</a></li>
	      	</c:if>
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
            <h2 class="title">Eydiz Partner</h2>
            <button type="button" class="btnBack btnFlat btnMobileNav">
              <span class="hidden">이전</span>
            </button>
            <button type="button" class="btnHome btnFlat btnMobileNav" onclick="location.href='<%=cp%>';">
              <span class="hidden">홈</span>
            </button>
          </div>
        </div>
      </header>