<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <form action="<%=cp %>/reward/${projectNo}/step2" method="post">
    <div class="rewardWrap">
      <div class="rewardInner">
      	<div class="rewardProcessWrap">
           <ul class="rewardProcess">
             <li><span>리워드 선택</span></li>
             <li><span>결제</span></li>
             <li class="on"><span>결제 완료</span></li>
           </ul>
         </div>
      </div>
      <div class="rewardInner padding">
      	<h2 class="titleLabel">결제가 완료되었습니다.</h2>
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
                   		<c:if test="${item.value.rewardOption != null}">
	                      <span class="rewardBuyOptionTitle">옵션:</span>
	                      <span class="rewardBuyOptionText">${item.value.optionAnswer}</span>
                   		</c:if>
                    </div>
                  </dt>
                  <dd>
                    <div class="rewardBuyInformation">
                      <span class="rewardBuyQuantity">수량: <fmt:formatNumber type = "number" pattern = "#,###" value = "${item.value.requestQuantity}" />개</span>
                      <span class="rewardBuyAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${item.value.purchasePrice}" />원</span>
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
                <dd><fmt:formatNumber type = "number" pattern = "#,###" value = "${sessionScope.reward.totalAmount}" />원</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt>배송비</dt>
                <dd><fmt:formatNumber type = "number" pattern = "#,###" value = "${sessionScope.reward.shipAmount}" />원</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt>최종결제금액</dt>
                <dd><strong class="finalAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${sessionScope.reward.finalAmount}" />원</strong></dd>
              </dl>
            </li>
          </ul>
        </div>
      </div>
      <%--div class="rewardInner padding">
		카카오페이 결제가 정상적으로 완료되었습니다.
		결제일시:     [[${kakaoPay.approved_at}]]<br/>
		주문번호:    [[${kakaoPay.partner_order_id}]]<br/>
		상품명:    [[${kakaoPay.item_name}]]<br/>
		상품수량:    [[${kakaoPay.quantity}]]<br/>
		결제금액:    [[${kakaoPay.amount.total}]]<br/>
		결제방법:    [[${kakaoPay.payment_method_type}]]<br/>
      </div --%>
      <div class="rewardInner padding center">
        <button type="button" class="btnSubmit noFull" onclick="location.href='<%=cp%>/detail/${sessionScope.reward.projectNo}'">돌아가기</button>
      </div>
    </div>
  </form>
</article>