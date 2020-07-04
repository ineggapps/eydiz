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
<nav class="row">
  <div class="rowFull">
    <ul class="detailNav">
      <li class="on">
        <a href="#"><span>스토리</span></a>
      </li>
      <li>
        <a href="#"><span>새소식</span><span class="badge">3</span></a>
      </li>
      <li>
        <a href="#"><span>커뮤니티</span><span class="badge">20</span></a>
      </li>
      <li>
        <a href="#"><span>서포터</span><span class="badge">1190</span></a>
      </li>
    </ul>
  </div>
</nav>
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
          <div class="progress">
            <div class="progressBar" style="width: ${project.projectAttainmentRate}%;"></div>
          </div>
          <p class="stateText"><strong>${project.projectAttainmentRate}</strong>% 달성</p>
          <p class="stateText"><strong>${project.projectAttainAmount}</strong>원 펀딩</p>
          <p class="stateText"><strong>000</strong>명의 서포터</p>
        </div>
        <a href="#" class="btnSubmit">펀딩하기</a>
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
          <div class="brandStatus">
            <div class="statusItem">
              <h5>평판</h5>
              <div class="progress">
                <div class="progressBar" style="width: 100%;"></div>
              </div>
            </div>
            <div class="statusItem">
              <h5>소통</h5>
              <div class="progress">
                <div class="progressBar warning" style="width: 22%;"></div>
              </div>
            </div>
            <div class="statusItem">
              <h5>인기</h5>
              <div class="progress">
                <div class="progressBar" style="width: 44%;"></div>
              </div>
            </div>
          </div>
          <div class="goToInquiry brandInfoBoxRow">
            <p>브랜드에게 문의하세요</p>
            <div class="btnComponent">
              <button class="btnPlain semiRound">
                <span class="icon talk"></span>브랜드에게 문의하기
              </button>
            </div>
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
          <div class="rewardItem" data-reward-no="${item.rewardNo}">
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
        <div class="rank rowBox border">
          <div class="rowBoxInner">
            <h4 class="title large">인기 프로젝트</h4>
            <ol class="rankList">
            <c:forEach var="item" items="${popularProject}" varStatus="status">
              <li>
                <p>
                  <span class="rankNumber">${status.count}</span
                  ><a href="<%=cp%>/detail/${item.projectNo}">${item.projectName}</a>
                </p>
              </li>
            </c:forEach>
            </ol>
          </div>
        </div>
      </div>
    </div>
  </div>
</article>