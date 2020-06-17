<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <div class="loginWrap">
    <h2>로그인</h2>
    <div class="loginForm">
      <form name="loginForm" action="<%=cp %>/member/login" method="post" >
        <div class="inputRow">
          <label>
            <input
              type="text"
              id="memberId"
              name="memberId"
              placeholder="아이디"
              class="inputBox ${error}"
            />
          </label>
        </div>
        <div class="inputRow">
          <label>
            <input
              type="password"
              id="memberPwd"
              name="memberPwd"
              placeholder="비밀번호(영문, 숫자, 특수문자 포함 8자 이상)" 
              class="inputBox ${error}"
            />
            <span class="desc ${error}"
              >등록되지 않은 아이디이거나, 아이디 또는 비밀번호가 회원정보와 일치하지
              않습니다.</span
            >
          </label>
        </div>
        <div class="inputRow loginOption">
          <label>
            <input type="checkbox" title="아이디 저장" id="saveUserId" />
            <span class="text">아이디 저장</span>
          </label>
          <p><a href="#" class="findAccount">아이디&centerdot;비밀번호 찾기</a></p>
        </div>
        <div class="inputRow">
          <button type="button" id="btnLogin" class="btnSubmit">로그인</button>
          <input type="hidden" name="returnURL" />
        </div>
        <div class="orRow"><span>또는</span></div>
        <div class="socialLogin">
          <ul class="inputRow flex">
            <li><button type="button">카카오</button></li>
            <li><button type="button">구글</button></li>
          </ul>
          <div class="inputRow">
            <p class="text">
              <span>아직 계정이 없나요? </span><a href="<%=cp %>/member/join" class="register">회원가입</a>
            </p>
          </div>
        </div>
      </form>
    </div>
  </div>
</article>
<script src="<%=cp%>/resource/js/member/login.js"></script>