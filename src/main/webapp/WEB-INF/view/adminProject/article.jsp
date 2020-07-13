<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
	$(function() {
		$(".projectRejectMemo").hide();
		$(".projectRejectBtn").click(function(){
			var isHidden = $(".projectRejectMemo").is(':hidden');
			$(".projectRejectMemo").hide();
			
			if(isHidden) {
				$(".projectRejectMemo").show();
			} else {
				$(".projectRejectMemo").hide();
			}
		});
	});
	
	
	function adminProjectSubmit() {
		var f = document.adminProjectSubmitForm;
		
		if(confirm("프로젝트를 승인하시겠습니까?")) {
			f.submit();
		}
		
	}
	
	
	function countCheckBox() {
		var chkbox = document.adminProjectRejectForm.statusMemo;
		var count = 0;
		
		for(i=0; i < chkbox.length; i++) {
			if(chkbox[i].checked==true) {
				count++;
			}
		}
		return count;
	}
	
	
	
	function adminProjectReject() {
		var form = document.adminProjectRejectForm;
				
		if(confirm("프로젝트를 반려하시겠습니까?")) {
			if(countCheckBox()> 0) {
				form.submit();
			} else {
				alert("반려사유를 하나 이상 선택해주십시오.");
				return;
			}
		}
	}
	
	$(document).ready(function(){

		// 일단 버튼을 숨긴다
		$("#back-top").hide();

		// 스크롤이 되면 버튼이 나타난다. 
		$(function () {
		   $(window).scroll(function () {
		        if ($(this).scrollTop() > 100) {
		              $('#back-top').fadeIn();
		         } else {
		              $('#back-top').fadeOut();
		         }
		});

		// 버튼 클릭하면, 맨 위로 이동!! 
		$('#back-top a').click(function () {
		        $('body,html').animate({
		              scrollTop: 0
		         }, 800);
		         return false;
		        });
		});

		});


</script>

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
          <p class="stateText">프로젝트 정보</p>
          <div class="progress">
            <div class="progressBar"></div>
          </div>
          <p class="stateText">시작일 : <strong> ${project.projectStartDate}</strong></p>
          <p class="stateText">종료일 : <strong> ${project.projectEndDate}</strong></p>
          <p class="stateText">목표금액 : <strong> <fmt:formatNumber type="number" maxFractionDigits="3" value="${project.projectGoalAmount}"></fmt:formatNumber></strong></p>
        </div>
        <c:if test="${statusNo == 1}">
        <form name="adminProjectSubmitForm" method="post">
	        <a onclick="adminProjectSubmit()" class="adminProjectBtn projectSubmitBtn">승인하기</a>
	        <input type="hidden" name="statusNo" value="5">
	        <input type="hidden" name="statusMemo" value="승인완료">
        </form>
        
        <form name="adminProjectRejectForm" method="post">
	        <a class="adminProjectBtn projectRejectBtn">반려하기</a>
	        <input type="hidden" name="statusNo" value="2">
	        <div class="projectRejectMemo">
	        	<div class="projectMemoCheckBox">
		        	<input type="checkbox" name="statusMemo" value="프로젝트 및 메이커의 신뢰성 부족">프로젝트 및 메이커의 신뢰성 부족 <br>
		        	<input type="checkbox" name="statusMemo" value="카테고리에 부합하지 않음">카테고리에 부합하지 않음 <br>
		        	<input type="checkbox" name="statusMemo" value="불명확한 자금 사용 계획">불명확한 자금 사용 계획 <br>
		        	<input type="checkbox" name="statusMemo" value="리워드 제공 가능성 낮음">리워드 제공 가능성 낮음<br>
		        	<input type="checkbox" name="statusMemo" value="타인에 대한 인신공격이나 비방">타인에 대한 인신공격이나 비방 <br>
		        	<input type="checkbox" name="statusMemo" value="정치적, 종교적, 성적 편향성이 포함">정치적, 종교적, 성적 편향성이 포함 <br>
		        	<input type="checkbox" name="statusMemo" value="불법적인 행위 조장">불법적인 행위 조장 <br>
		        	
		        	<button type="button" class="projectRejectSubmit" onclick="adminProjectReject();">확인</button>
	        	</div>
	        </div>
        </form>
        </c:if>
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
          <h3>리워드</h3>
          <c:forEach var="item" items="${rewards}">
          <div class="adminRewardItem" data-reward-no="${item.rewardNo}">
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
              </div>
            </div>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
        <div class = "back-topBox">
	        <p id="back-top">
			   <a href="#top">Back To Top</a>
			</p>
		</div>
  </div>
</article>