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
        <a href="<%=cp %>/main" class="menu on"><span>펀딩하기</span></a>
      </li>
      <li>
        <a href="<%=cp %>/awards/list" class="menu"><span>어워즈</span></a>
      </li>
      <li>
        <a href="<%=cp %>/partner/list" class="menu"><span>파트너</span></a>
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
		<li><a href="<%=cp%>/mypage/myInfo" class="profilePic">
			<c:if test="${sessionScope.member.memberImageUrl!=null}">
			<span class="displayAvatar" style="background-image:url('${sessionScope.member.memberImageUrl}')">&nbsp;</span>
			</c:if>
			<c:if test="${sessionScope.member.memberImageUrl==null}">
			<span class="displayAvatar">&nbsp;</span>
			</c:if>
			${sessionScope.member.memberNickname}님
			</a></li>
    	</c:if>
    	</ul>
    </div>
  </div>
  <div class="headerInnerMobile">
  <div class="mobileNav">
    <div class="mobileNavLeft">
	    <h2>eydiz</h2>
    </div>
    <div class="mobileNavRight">
		<a href="<%=cp %>/mypage/myInfo">
		<c:if test="${sessionScope.member.memberImageUrl!=null}">
		<span class="displayAvatar" style="background-image:url('${sessionScope.member.memberImageUrl}')">&nbsp;</span>
		</c:if>
		<c:if test="${sessionScope.member.memberImageUrl==null}">
		<span class="displayAvatar">&nbsp;</span>
		</c:if>
		<c:if test="${sessionScope.member!=null}">
			${sessionScope.member.memberNickname}님
		</c:if>
		</a>
    </div>
  </div>
  </div>
  <%--div class="headerInnerTab">
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
  </div --%>
</header>