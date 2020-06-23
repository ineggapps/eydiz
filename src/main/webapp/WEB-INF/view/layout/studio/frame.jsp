<%@ page contentType="text/html; charset=UTF-8"%> <%@ page trimDirectiveWhitespaces="true" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib prefix="tiles"
uri="http://tiles.apache.org/tags-tiles" %> <% String cp=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>크라우드 펀딩</title>
    <link rel="stylesheet" href="<%=cp%>/resource/css/reset.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/jquery/css/jquery-ui.min.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/layout.css" />
    <link rel="stylesheet" href="<%=cp%>/resource/css/studio/reward.css" />
    <!--[if lt IE 9]>
      <script src="<%=cp%>/resource/js/html5shiv.min.js"></script>
    <![endif]-->
    <script>
      const cp = "<%=cp%>";
    </script>
    <script src="<%=cp%>/resource/js/jquery-3.5.1.min.js"></script>
    <script src="<%=cp%>/resource/jquery/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery.form.js"></script>
    <script type="text/javascript" src="<%=cp %>/resource/js/lib/bluebird.js"></script>
    <script src="<%=cp %>/resource/js/studio/brand_studio.js"></script>
    <script type="text/javascript">
	    $(function () {
	  	  $("#startShippingDate").datepicker({ dateFormat: "yymmdd" });
	  	});
    </script>
  </head>
  <body>
    <div id="wrap">
      <div class="overlay"></div>
      <header id="header">
        <div class="headerInner">
          <h3 class="btnMenu" id="btnGnbMenu"><span class="hidden">메뉴</span></h3>
          <h1 class="logo"><a href="#">Brand studio</a></h1>
          <div class="projectTitle"><h2>${sessionScope.brand.brandName}</h2></div>
          <ul class="rightGnb">
            <li class="menuExit">
              <a href="<%=cp %>/studio/project/list" class="none"><span>나가기</span></a>
            </li>
            <li class="menuTemporary hide">
              <a href="#"><span>임시저장</span></a>
            </li>
            <!-- <li class="menuPreview">
              <a href="#"><span>미리보기</span></a>
            </li> -->
          </ul>
        </div>
      </header>
      <main id="content">
        <div class="contentWrapper">
          <div class="columnSide">
            <tiles:insertAttribute name="side" />
          </div>
          <div class="columnContent">
            <tiles:insertAttribute name="content" />
          </div>
        </div>
      </main>
      <!-- reward modal -->
      <div class="rewardOverlay">
        <div class="rewardModalWrapper">
        <form action="." method="post" name="rewardForm">
          <div class="rewardModal">
            <h2 class="title">
              리워드 추가<a href="#" class="btnRewardClose"><span class="hidden">닫기</span></a>
            </h2>
            <div class="infoItem required">
              <div class="inputWrap">
                <label for="amount">금액</label>
                <div class="inputBox">
                  <input type="text" id="amount" name="amount" placeholder="금액" /> 원
                  <p class="desc">금액을 1,000원 이상 입력해야 합니다.</p>
                </div>
              </div>
            </div>
            <div class="infoItem required">
              <div class="inputWrap">
                <label for="rewardTitle">리워드명</label>
                <div class="inputBox">
                  <input
                    type="text"
                    id="rewardTitle"
                    name="rewardTitle"
                    placeholder="타이틀 입력 ex) [이디즈 푸드]기본 리워드"
                  />
                </div>
              </div>
            </div>
	          <div class="infoItem required">
	            <div class="inputWrap">
	              <label for="rewardContent">상세 설명</label>
	              <div class="inputBox">
	                <textarea id="rewardContent" name="rewardContent" maxlength="400"></textarea>
	              </div>
	            </div>
	          </div>
	          <div class="infoItem required">
	            <div class="inputWrap">
	              <label for="rewardOption">옵션 조건</label>
	              <div class="inputBox">
	                <select id="rewardOptionExist" name="rewardOptionExist">
	                	<option value="0">옵션 없음</option>
	                	<option value="1">직접 입력 옵션</option>
	                </select>
	              </div>
	            </div>
	            <div class="inputWrap hide">
	            	<div class="infoItem">
		                <textarea id="rewardOption" name="rewardOption" maxlength="400"></textarea>
	            	</div>
	            </div>
	          </div>
	          <div class="infoItem required">
	            <div class="inputWrap">
	              <label class="no-margin-bottom">배송 조건</label>
	              <div class="inputBox clear custom">
	              	<label>
	              		<input type="radio" name="isShipping" value="1">
	              		<span class="radiomark"></span>
	              		<span class="exp">배송이 필요한 리워드입니다.</span>	              	
	              	</label>
	              </div>
	              <div class="inputWrap margin-height indent shippingOptionWrap">
	              	<label class="margin-bottom-some narrow-width">배송비</label>
	              	<input
	                    type="text"
	                    id="shipAmount"
	                    name="shipAmount"
                 	/> 원
	              </div>
	              <div class="inputBox clear custom">
	              	<label>
	              		<input type="radio" name="isShipping" value="0" checked="checked"> 
	              		<span class="radiomark"></span>
	              		<span class="exp">배송이 필요없는 리워드입니다.</span>              	
	              	</label>
	              </div>
	            </div>
	          </div>
			<div class="infoItem required">
              <div class="inputWrap">
                <label for="limitQuantity">제한수량</label>
                <div class="inputBox">
                  <input type="text" id="limitQuantity" name="limitQuantity" placeholder="제한수량 입력" /> 개
                </div>
              </div>
            </div>
			<div class="infoItem required">
              <div class="inputWrap">
                <label for="startShippingDate">발송시작일</label>
                <div class="inputBox">
                  <input type="text" id="startShippingDate" name="startShippingDate" placeholder="YYYYMMDD" />
                </div>
              </div>
            </div>
            <div class="infoItem rewardController">
            	<button class="btnSubmit" type="button">등록</button>
            	<button class="btnPlain btnRewardClose" type="button">취소</button>
            </div>
          </div>
        </form>
        </div>
        <div class="headerInnerMobile">
          <div class="mobileNav">
            <h2 class="title">리워드 추가</h2>
            <a href="#" class="btnClose btnRewardClose btnFlat btnMobileNav">
              <span class="hidden">닫기</span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
