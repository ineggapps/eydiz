<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <h2>리워드 설계</h2>
  <p class="description">서포터에게 제공할 리워드 내용을 입력하세요. 서포터가 쉽게 펀딩할 수 있는 저렴한 리워드부터 서포터의 보다 빠른 펀딩을 유도할 수 있는 얼리버드 리워드까지 다양한 리워드를 구성하면 좋습니다.</p>
  <div class="infoItemWrap">
	<form name="projectForm" action="<%=cp %>/studio/project/${projectNo}/register" method="post" enctype="multipart/form-data">
	<input type="hidden" name="projectNo" value="${projectNo}"/>
	<input type="hidden" name="brandNo" value="${sessionScope.brand.brandNo}"/>
    <div class="rewardContentCard">
    	<h4>리워드 설계 조건</h4>
    	<dl>
    		<dt>정렬 순서</dt>
    		<dd>금액이 낮은 순으로 자동정렬됩니다.</dd>
    		<dt>제한 수량</dt>
    		<dd>리워드의 총 수량으로 제공할 수 있는 총량입니다. 해당 수량이 모두 펀딩되면 더는 펀딩에 응할 수 없습니다.</dd>
    		<dt>발송 시작일</dt>
    		<dd>프로젝트 종료일 이후부터 3개월 이내로 설계 가능합니다.</dd>
    	</dl>
    </div>
    <div class="infoItem">
	    <button type="button" id="btnAddReward" class="btnDarkContrast">
			<span class="icon iconPlus">&nbsp;</span>추가하기
		</button>
    </div>
    <div class="infoItem">
    	<ul class="rewardSnippetList">
    		
    	</ul>
		<div class="rewardSnippet dummy">
			<div class="rewardSnippetInner">
				<div class="snippetTitle">
					<h5 class="dbColumnAmount">50,000원</h5>
					<span class="highlight dbColumnLimitQuantity">제한 수량 50개</span>
				</div>
				<div class="snippetContent">
					<h6 class="dbColumnTitle">리워드 제목</h6>
					<p class="content dbColumnContent">리워드 내용</p>
					<dl>
						<dt>배송비</dt>
						<dd class="dbColumnShipAmount">5,000원</dd>
						<dt>리워드 발송 시작일</dt>
						<dd class="dbColumnShippingStartDate">2020년 10월 20일(예정)</dd>
					</dl>
					<div class="snippetController">
						<button type="button" class="btnSnippet btnSnippetDelete">삭제</button>
						<button type="button" class="btnSnippet btnSnippetEdit">편집</button>
					</div>
				</div>
			</div>
		</div>
    </div>
	<%--button type="button" class="btnSubmit" id="btnSave">등록</button--%>
  </form>
  </div>
  <script type="text/javascript" src="<%=cp%>/resource/js/studio/project_reward.js"></script>
</article>