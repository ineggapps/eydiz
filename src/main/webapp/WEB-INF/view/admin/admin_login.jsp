<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/reset.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/admin/admin_login.css" />
    <title>회원 로그인</title>
    <!--[if lt IE 9]>
    <script src="<%=cp%>/resource/js//html5shiv.min.js"></script>
    <![endif]-->
    <script src="<%=cp%>/resource/js/jquery-3.5.1.min.js"></script>
	<script>
		const backgrounds = [
			"https://images.pexels.com/photos/348523/pexels-photo-348523.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
			"https://images.pexels.com/photos/390051/surfer-wave-sunset-the-indian-ocean-390051.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
			"https://images.pexels.com/photos/2707756/pexels-photo-2707756.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
			"https://images.unsplash.com/photo-1424581342241-2b1aba4d3462?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1651&q=80",
			"https://images.pexels.com/photos/2090645/pexels-photo-2090645.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
		];
		const idx = Math.floor(Math.random() * backgrounds.length);
		$(function(){
			$(".background_wrapper").css("background-image", "url('"+ backgrounds[idx] +"')");
		});
	</script>    
  </head>
  <body>
    <div id="login_wrap">
      <div class="background_wrapper"></div>
      <div class="background_overlay"></div>
      <div id="login_header">
        <h1>
          <a href="<%=cp%>"><span><strong>관리자</strong> 로그인</span></a>
        </h1>
      </div>
      <div id="login_content">
        <div id="login_form">
          <form action="<%=cp%>/member/login" method="post">
            <fieldset>
              <legend><strong>관리자</strong> 로그인</legend>
              <div class="login_item">
                <input
                  class="text_form"
                  type="text"
                  name="memberId"
                  id="user_id"
                  placeholder="관리자 아이디"
                />
              </div>
              <div class="login_item">
                <input
                  class="text_form"
                  type="password"
                  name="memberPwd"
                  id="user_password"
                  placeholder="비밀번호"
                />
              </div>
              <div class="login_item login_item_deep">
                <%--input type="checkbox" name="check_account" id="check_account" /><label
                  for="check_account"
                  >로그인 상태 유지</label
                > --%>
                <c:if test="${not empty error}">
                <div id="errorMessage">
                	<p class="message">등록되지 않은 아이디이거나, 아이디 또는 비밀번호가 회원정보와 일치하지
              않습니다.</p>
                </div>
                </c:if>
              </div>
              <div class="login_item">
                <!--링크만 걸었음-->
                <button class="login_button" type="submit">로그인</button>
              </div>
            </fieldset>
          </form>
          <!-- <div class="login_item" id="join_box">
            <a href="#" class="join_user">회원가입</a>
            <ul>
              <li><a href="#">아이디 찾기</a></li>
              <li><a href="#">비밀번호 찾기</a></li>
            </ul>
          </div> -->
        </div>
      </div>
      <div id="login_footer">
        <hr id="border" />
        <!-- <div class="fnb">
          <a href="#">이용약관</a>
          <a href="#">개인정보 처리방침</a>
          <a href="#">운영정책</a>
          <a href="#">고객센터</a>
          <a href="#">공지사항</a>
        </div> -->
        <small>Copyright &copy;<a href="#">Eydiz Company.</a> All rights reserved.</small>
      </div>
    </div>
  </body>
</html>
