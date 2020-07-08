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
		      
		카카오페이 결제가 정상적으로 완료되었습니다.
		 
		결제일시:     [[${kakaoPay.approved_at}]]<br/>
		주문번호:    [[${kakaoPay.partner_order_id}]]<br/>
		상품명:    [[${kakaoPay.item_name}]]<br/>
		상품수량:    [[${kakaoPay.quantity}]]<br/>
		결제금액:    [[${kakaoPay.amount.total}]]<br/>
		결제방법:    [[${kakaoPay.payment_method_type}]]<br/>
		 
 
      </div>
      <div class="rewardInner padding center">
        <button type="button" class="btnSubmit noFull" onclick="location.href='<%=cp%>/detail/${sessionScope.reward.projectNo}'">돌아가기</button>
      </div>
    </div>
  </form>
</article>