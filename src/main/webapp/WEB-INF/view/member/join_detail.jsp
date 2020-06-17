<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <div class="registerWrap">
    <h2 class="small">회원가입</h2>
    <div class="loginForm">
      <form name="loginForm" method="post" action="<%=cp %>/member/join">
        <div class="inputRow">
          <label class="checkbox">
            <input type="checkbox" name="chkAgreement" />
            <span class="checkmark"></span><span class="text">전체 동의</span>
          </label>
        </div>
        <div class="inputRow">
          <label>
            <input
              type="text"
              name="memberId"
              id="memberId"
              placeholder="아이디"
              class="inputBox"
            />
            <span class="loadingBar">&nbsp;</span>
            <span class="desc">아이디가 올바르지 않습니다.</span>
          </label>
        </div>
        <div class="inputRow">
          <label>
            <input
              type="password"
              name="memberPwd"
              id="memberPwd"
              placeholder="비밀번호(영문, 숫자, 특수문자 포함 8자 이상)"
              class="inputBox"
            />
            <span class="desc"
              >비밀번호가 형식에 맞지 않습니다. 영문, 숫자, 특수문자 포함 8자 이상이어야
              합니다.</span
            >
          </label>
        </div>
        <div class="inputRow">
          <label>
            <input
              type="password"
              name="memberPwdConfirm"
              id="memberPwdConfirm"
              placeholder="비밀번호 확인"
              class="inputBox"
            />
            <span class="desc">비밀번호가 일치하지 않습니다.</span>
          </label>
        </div>
		<div class="inputRow">
          <label>
            <input
              type="text"
              name="memberEmail"
              id="memberEmail"
              placeholder="이메일"
              class="inputBox"
            />
            <span class="loadingBar">&nbsp;</span>
            <span class="desc">이메일 주소가 올바르지 않습니다.</span>
          </label>
        </div>
        <div class="inputRow">
          <label>
            <input type="text" name="memberNickname" id="memberNickname" placeholder="닉네임" class="inputBox" />
            <span class="desc">닉네임을 올바르게 입력해 주세요.</span>
          </label>
        </div>
        <%--div class="inputRow">
          <label class="inputEvent">
            <span class="title">이벤트 참여</span>
            <input type="text" name="promotionCode"" placeholder="이벤트 코드 입력"
            class="inputBox" />
            <span class="desc show"
              >이벤트 참여 란에 [펀딩만세]를 입력하면 신규회원 가입 기념 3,000포인트를
              드립니다!</span
            >
          </label>
        </div--%>
        <div class="inputRow">
          <button
            type="button"
            id="btnSubmit"
            class="btnSubmit disabled"
            disabled="disabled"
          >
            완료
          </button>
          <input type="hidden" name="returnURL" />
        </div>
      </form>
    </div>
  </div>
  <script src="<%=cp %>/resource/js/member/join_detail.js"></script>
</article>