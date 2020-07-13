<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
    <h2>펀딩 결제/후원 내역 조회</h2>
    <p class="description">
           펀딩  결제/후원 상세 내용입니다.
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
	   	</div>
	</div>
	<c:forEach items="${rewards}" var="reward">
	<div class="historyRewardItem">
		<p class="historyRewardTitle">${reward.rewardTitle}</p>
		<p class="historyRewardAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${reward.amount * reward.requestQuantity}" />원 / ${reward.requestQuantity}개</p>
		<p class="historyRewardStartShippingDate">${reward.startShippingDate}(예정)</p>
	</div>
	</c:forEach>
</article>