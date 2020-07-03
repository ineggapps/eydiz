<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <form action=".">
    <div class="rewardWrap">
      <div class="rewardInner">
      	<div class="rewardProcessWrap">
           <ul class="rewardProcess">
             <li><span>리워드 선택</span></li>
             <li class="on"><span>결제 예약</span></li>
             <li><span>결제 완료</span></li>
           </ul>
         </div>
      </div>
      <div class="rewardInner padding">
        <div class="rewardBuyListWrapper">
          <ul class="rewardBuyList">
          	<c:forEach var="item" items="${sessionScope.reward.rewards}">
            <li>
              <div class="rewardBuyItem">
                <dl>
                  <dt>
                    <p class="rewardBuyTitle">${item.value.rewardTitle}</p>
                    <p class="rewardBuyText">${item.value.rewardContent}</p>
                    <div class="rewardBuyOption">
                      <span class="rewardBuyOptionTitle">옵션:</span>
                      <span class="rewardBuyOptionText">${item.value.optionAnswer}</span>
                    </div>
                  </dt>
                  <dd>
                    <div class="rewardBuyInformation">
                      <span class="rewardBuyQuantity">수량: ${item.value.requestQuantity}개</span>
                      <span class="rewardBuyAmount">${item.value.purchasePrice}원</span>
                    </div>
                  </dd>
                </dl>
              </div>
            </li>
            </c:forEach>
          </ul>
          <ul class="rewardBuySummary">
            <li>
              <dl>
                <dt>펀딩금액</dt>
                <dd>${sessionScope.reward.totalAmount}</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt>배송비</dt>
                <dd>${sessionScope.reward.shipAmount}</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt>최종결제금액</dt>
                <dd>${sessionScope.reward.finalAmount}</dd>
              </dl>
            </li>
          </ul>
        </div>
      </div>
      <div class="rewardInner padding">
        <div class="rewardShippingWrapper">
          <h3><span>리워드 배송지</span></h3>
          <div class="rewardShippingInput">
            <div class="rewardShippingItem">
              <span class="inputLabel">이름</span>
              <input type="text" name="recipient" />
            </div>
            <div class="rewardShippingItem">
              <span class="inputLabel">휴대폰 번호</span>
              <input type="text" name="phone" />
            </div>
            <div class="rewardShippingItem">
              <span class="inputLabel">주소</span>
              <button type="button" class="btnSubmit noFull">우편번호 검색</button>
              <span class="address1"></span>
              <input type="hidden" name="address1" />
              <input type="text" name="address2" placeholder="상세 주소" />
            </div>
          </div>
        </div>
      </div>
      <div class="rewardInner padding center">
        <button type="submit" class="btnSubmit noFull">결제 요청</button>
      </div>
    </div>
  </form>
</article>