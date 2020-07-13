<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>
<article class="row">
    <h2>펀딩 내역 조회</h2>
    <p class="description">
      현재까지 진행된 펀딩 내역입니다.
    </p>
	<div class="historyItemWrap">
	   	<div class="historyItem">
	   		<p class="historyCategory">${project.categoryName}</p>
			<p class="historyProjectStatus">
				<i class="projectStatus ${project.statusNo==5?'activate':'expire'}"></i>
				<span>${project.statusNo==5?"진행 중":"종료"}</span>
			</p>
	   		<p class="historyProjectName">${project.projectName}</p>
	   		<p class="historyBrandName">by ${project.brandName}</p>
	   		<p class="historyStatus ${project.isCanceled==0?'blue':'red'}">${project.isCanceled==0?"결제 완료":"결제 취소됨"}</p>
	   		<p class="historyDate">${project.boughtDate} 펀딩</p>
	   		<c:if test="${project.isCanceled!=1 and project.remainDays >= 0}">
	   		<p class="historyController">
	   			<button type="button" class="btnFlat full border" id="cancelFunding" data-buy-no="${project.buyNo}">결제 취소</button>
	   		</p>
	   		</c:if>
	   	</div>
	</div>
	<c:forEach items="${rewards}" var="reward">
	<div class="historyRewardItem">
		<p class="historyRewardTitle">${reward.rewardTitle}</p>
		<p class="historyRewardAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${reward.amount * reward.requestQuantity}" />원 / ${reward.requestQuantity}개</p>
		<p class="historyRewardStartShippingDate">${reward.startShippingDate}(예정)</p>
	</div>
	</c:forEach>
	<div class="historyRewardPayment">
		<p class="historyRewardAmount"><span class="left">리워드 금액</span><span class="right"><fmt:formatNumber type = "number" pattern = "#,###" value = "${project.totalAmount}"/>원</span> </p>
		<p class="historyRewardAmount"><span class="left">배송비</span><span class="right"><fmt:formatNumber type = "number" pattern = "#,###" value = "${project.shipAmount }"/>원</span> </p>
		<p class="historyRewardAmount"><span class="left">최종 결제 금액</span><span class="right"><strong><fmt:formatNumber type = "number" pattern = "#,###" value = "${project.finalAmount}"/>원</strong></span> </p>
	</div>
</article>
<script>
$(function(){
	$("#cancelFunding").click(function(){
		if(!confirm("펀딩을 취소하시겠습니까?")){
			return;
		}
		const buyNo = $(this).data("buy-no");
		const url = cp + "/mypage/cancel";
		ajaxJSON(url, "post", {buyNo:buyNo}).then(function(data){
			if(data.result=="ok"){
				location.reload(true);
			}
		}).catch(function(e){
			console.log(e);
		})
	});
});
</script>