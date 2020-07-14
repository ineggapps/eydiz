<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>



      <header id="header">
        <div class="headerInner">
          <h1 class="logo"><a href="<%=cp%>/">eydiz</a></h1>
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
            <h2 class="title">Eydiz Awards</h2>
            <button type="button" class="btnBack btnFlat btnMobileNav">
              <span class="hidden">이전</span>
            </button>
            <button type="button" class="btnHome btnFlat btnMobileNav">
              <span class="hidden">홈</span>
            </button>
          </div>
        </div>
        <div class = "awardsTitleImage" >
        	<div class="awardsTitle">
        		<div class = "awardsTitleText">Eydiz Awards</div>
        		<div class = "awardsTitleSubText">
        		   <p>이디즈를 찾아준</p><br>
				   <p>메이커 / 서포터 / 투자자 분들 고맙습니다.</p><br>
				   <p>도전하고 지지하는 여러분의 가치 있는 행보를 응원합니다.</p><br><br>
				   <p class = "awardTitlePoint">여러분을 대표해서</p><br>
				   <p class = "awardTitlePoint">메이커/프로젝트 15을 선정하여 이 상을 전합니다.</p><br>
				</div>
        	</div>
        </div>
         <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li class="on">
                  <a href="<%=cp%>/awards/list"><span>#Eydiz Awards 15</span></a>
                </li>
              </ul>
            </div>
          </nav>
      </header>