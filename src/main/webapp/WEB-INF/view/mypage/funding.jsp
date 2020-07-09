<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
    <h2>펀딩 내역 조회</h2>
    <p class="description">
      현재까지 진행된 펀딩 내역입니다.
    </p>
    <ul class="historyFundingList">
    	<li class="">
    	    <div class="historyItemWrap">
		    	<div class="historyItem">
		    		<p class="historyCategory">펀딩/홈 리빙</p>
					<p class="historyProjectStatus"><span><i class="projectStatus activate"></i> 진행 중</span></p>
		    		<p class="historyProjectName">피곤하고 땡땡한 종아리 때문이라면</p>
		    		<p class="historyBrandName">by 나인테크놀로지</p>
		    		<p class="historyStatus blue">결제 예약 취소</p>
		    		<p class="historyDate">2020. 07. 05. 펀딩</p>
		    	</div>
		    </div>
    	</li>
    </ul>
</article>
