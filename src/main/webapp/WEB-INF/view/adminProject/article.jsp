<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <div class="rowFull">
    <div class="projectTitle">
      <div
        class="overlay"
        style="
          background-image: url('${project.projectImageUrl}');
        "
      ></div>
      <h3><span>${project.categoryName}</span></h3>
      <h2>${project.projectName}</h2>
    </div>
  </div>
</article>
<article class="row">
  <div class="rowFull">
    <div class="detailWrap">
      <div class="detailContent">
		<div class="detailSummary">
		<p>
			<img class="fullWidth" src="${project.projectImageUrl}" alt="${project.projectName}" />
		</p>
		<p>
			${project.projectSummary}
		</p>
		</div>
		<hr class="divider deeper" />
      	<div class="detailStory">
		${project.projectStory}
      	</div>
      </div>
      <div class="detailSide">
        <div class="stateBox">
          <p class="stateText">13일 남음</p>
          <p class="stateText"><strong>${project.projectAttainAmount}</strong>원 펀딩</p>
          <p class="stateText"><strong>000</strong>명의 서포터</p>
        </div>
        <a href="#" class="btnSubmit">펀딩하기</a>
        <a href="#" class="btnRefuse">펀딩하기</a>
        <ul class="detailSideController">
          <li>
            <button type="button" class="btnPlain" id="btnLike"><span class="icon heart ${project.myLikeCount==1?'on':''}"></span><span class="count">${project.likeCount}</span></button>
          </li>
          <li>
            <button type="button" class="btnPlain"><span class="icon talk"></span>문의</button>
          </li>
          <li>
            <button type="button" class="btnPlain"><span class="icon share"></span>공유</button>
          </li>
        </ul>
        <div class="brandInfoBox">
          <div class="brandName">
            <a href="#"><span class="image"></span><span class="name">${project.brandName}</span></a>
          </div>
          <div class="brandInfoBoxRow">
            <p>브랜드 정보</p>
            <div class="brandInfoRowInner">
              <p>브랜드 연락처</p>
              <ul>
                <li>${project.managerEmail}</li>
                <li>${project.managerPhone}</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="rewards rowBox">
          <h3>리워드 선택</h3>
          <c:forEach var="item" items="${reward}">
          <div class="rewardItem">
            <div class="overlay">
              <p class="text">이 리워드 펀딩하기</p>
            </div>
            <dl class="rewardInfo">
              <dt>${item.amount}원 펀딩</dt>
              <dd>
                <p>${item.rewardTitle}</p>
                <c:if test="${not empty item.rewardOption}">
                <p>${item.rewardOption}</p>
                </c:if>
              </dd>
            </dl>
            <c:if test="${item.isShipping==1}">
            <dl class="rewardOptions">
              <dt>배송비</dt>
              <dd>${item.shipAmount}원</dd>
              <dt>리워드 발송 시작일</dt>
              <dd>${item.startShippingDate}</dd>
            </dl>
            </c:if>
            <div class="rewardQuantity">
              <div class="quantityRow mint">
                <span class="limitQuantity">제한수량 <strong>${item.limitQuantity}</strong>개</span>
                <span class="remainingQuantity highlighting">
                  현재 <strong>${item.remainQuantity}</strong>개 남음!
                </span>
              </div>
              <div class="quantityRow">
                <span class="soldQuantity">총 00개 펀딩완료</span>
              </div>
            </div>
          </div>
          </c:forEach>
        </div>
        <div class="reports rowBox border">
          <div class="rowBoxInner">
            <h4>신고하기란?</h4>
            <p class="gray small">
              해당 프로젝트에 허위내용 및 지적재산권을 침해한 내용이 있으면 제보해주시기
              바랍니다.
            </p>
            <a href="#" id="reportProject" class="btnPlain gray semiRound"
              >프로젝트 신고하기</a
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</article>