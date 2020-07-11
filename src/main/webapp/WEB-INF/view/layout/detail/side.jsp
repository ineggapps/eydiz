<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="stateBox">
  <p class="stateText">
  	<c:if test="${project.remainDays>0}">
	  	<strong id="cuRemainDays">${project.remainDays}</strong>일 남음
  	</c:if>
  	<c:if test="${project.remainDays==0}">
	  	오늘 마감!
  	</c:if>
  	<c:if test="${project.remainDays<0}">
	  	마감
  	</c:if>
  </p>
  <div class="progress">
    <div class="progressBar" style="width: ${project.attainRate*100>100?100:project.attainRate*100}%;"></div>
  </div>
  <p class="stateText"><strong id="cuAttainRate"><fmt:formatNumber type = "number" pattern = "###.#" value = "${project.attainRate*100}" /></strong>% 달성</p>
  <p class="stateText"><strong id="cuTotalAmount"><fmt:formatNumber type = "number" pattern = "####" value = "${project.totalAmount}" /></strong>원 펀딩</p>
  <p class="stateText"><strong id="cuSupportCount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${project.supportCount}" /></strong>명의 서포터</p>
</div>
<c:if test="${project.remainDays>=0}">
<a href="<%=cp %>/reward/${projectNo}/step1" class="btnSubmit">펀딩하기</a>
</c:if>
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
    <a href="#">
    <c:if test="${project.memberImageUrl==null}">
    <span class="image"></span>
    </c:if>
    <c:if test="${project.memberImageUrl!=null}">
    <span class="image" style="background-image:url('${project.memberImageUrl}')"></span>
    </c:if>
    <span class="name">${project.brandName}</span></a>
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
<c:if test="${item.remainDays>=0}">
<div class="rewards rowBox">
  <h3>리워드 선택</h3>
  <c:forEach var="item" items="${reward}">
  <div class="rewardItem" data-reward-no="${item.rewardNo}">
    <c:if test="${item.remainQuantity>0}">
    <div class="overlay">
      <p class="text">이 리워드 펀딩하기</p>
    </div>
    </c:if>
    <c:if test="${item.remainQuantity<=0}">
    <div class="overlay black">
      <p class="text">제한 수량이<br/>모두 펀딩되었습니다.</p>
    </div>
    </c:if>
    <dl class="rewardInfo">
      <dt><fmt:formatNumber type = "number" pattern = "#,###" value = "${item.amount}" />원 펀딩</dt>
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
      <dd><fmt:formatNumber type = "number" pattern = "#,###" value = "${item.shipAmount}" />원</dd>
      <dt>리워드 발송 시작일</dt>
      <dd>${item.startShippingDate}</dd>
    </dl>
    </c:if>
    <div class="rewardQuantity">
	  <c:if test="${item.remainQuantity>0}">
      <div class="quantityRow mint">
        <span class="limitQuantity">제한수량 <strong><fmt:formatNumber type = "number" pattern = "#,###" value = "${item.limitQuantity}" /></strong>개</span>
        <span class="remainingQuantity highlighting">
          현재 <strong><fmt:formatNumber type = "number" pattern = "#,###" value = "${item.remainQuantity}" /></strong>개 남음!
        </span>
      </div>
	  </c:if>
	  <c:if test="${item.remainQuantity<=0}">
      <div class="quantityRow">
      	<span class="highlighting red"><strong>제한수량이 모두 펀딩되었습니다.</strong></span>
	  </div>			  
	  </c:if>
      <div class="quantityRow">
        <span class="soldQuantity">총 <fmt:formatNumber type = "number" pattern = "#,###" value = "${item.limitQuantity - item.remainQuantity}" />개 펀딩완료</span>
      </div>
    </div>
  </div>
  </c:forEach>
</div>
</c:if>
<%--div class="reports rowBox border">
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
</div> --%>
<div class="rank rowBox border">
  <div class="rowBoxInner">
    <h4 class="title large">인기 프로젝트</h4>
    <ol class="rankList">
    <c:forEach var="item" items="${popularProject}" varStatus="status">
      <li>
	    <span class="rankNumber">${status.count}</span>
        <p class="rankItem">
          <a class="rankText" href="<%=cp%>/detail/${item.projectNo}">${item.projectName}</a>
        </p>
      </li>
    </c:forEach>
    </ol>
  </div>
</div>
<script>
$(function(){
	$(".rankItem").dotdotdot({
		ellipsis: "...",
	})
})
</script>