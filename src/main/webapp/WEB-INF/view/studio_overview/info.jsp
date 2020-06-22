<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <form name="<%=cp %>/studio/brand/info" method="post">
    <h2>브랜드 정보 확인&centerdot;수정</h2>
    <p class="description">
      안녕하세요. 본격적으로 프로젝트 작성을 시작하기 전에 간단한 정보를 입력하세요
    </p>

    <div class="infoItemWrap">
      <div class="infoItem required">
        <div class="infoTitle"><span>브랜드 이름</span></div>
        <p class="desc">펀딩이나 후원을 하며 알릴 브랜드의 이름을 입력하세요</p>
        <div class="inputWrap">
          <input type="text" id="brandName" name="brandName" value="${brand.brandName}" />
        </div>
      </div>

      <div class="infoItem">
        <div class="infoTitle"><span>관리자 이름</span></div>
        <p class="desc">프로젝트를 담당할 관리자의 이름을 입력하세요.</p>
        <div class="inputWrap">
          <input type="text" id="managerName" name="managerName" value="${brand.managerName}" />
        </div>
      </div>
      <div class="infoItem">
        <div class="infoTitle"><span>관리자 이메일</span></div>
        <p class="desc">프로젝트를 담당할 관리자의 이메일을 입력하세요.</p>
        <div class="inputWrap">
          <input type="text" id="managerEmail" name="managerEmail" value="${brand.managerEmail}"/>
        </div>
      </div>
      <div class="infoItem">
        <div class="infoTitle"><span>관리자 연락처</span></div>
        <p class="desc">프로젝트를 담당할 관리자의 연락처를 입력하세요.</p>
        <div class="inputWrap">
          <input type="text" id="managerPhone" name="managerPhone" value="${brand.managerPhone}"/>
        </div>
      </div>
    </div>
    <div class="submitContainer">
      <button type="submit" class="btnSubmit width60per">수정하기</button>
    </div>
  </form>
</article>
