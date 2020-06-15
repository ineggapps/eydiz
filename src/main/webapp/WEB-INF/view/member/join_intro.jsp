<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
 <article class="row">
  <div class="registerContainer">
    <div class="registerWrap">
      <h2>회원가입</h2>
      <p class="registerText">신규회원 가입하고<br />다양한 혜택을 받아보세요</p>
      <div class="loginForm"></div>
      <div class="socialLogin">
        <ul class="inputRow flex">
          <li><button type="button">카카오</button></li>
          <li><button type="button">구글</button></li>
        </ul>
        <div class="orRow"><span>또는</span></div>
        <div class="inputRow">
          <a href="?step=2" id="btnRegister" class="btnPlain">이메일로 가입</a>
          <input type="hidden" name="returnURL" />
        </div>
        <div class="inputRow">
          <p class="text">
            <span>이미 계정이 있나요? </span><a href="<%=cp %>/member/login" class="register">로그인</a>
          </p>
        </div>
      </div>
    </div>
    <%--div class="registerPicture"></div--%>
  </div>
  
</article>