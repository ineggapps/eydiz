<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <form action="<%=cp %>/reward/${projectNo}/step2" method="post" name="shippingLocationForm">
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
              <span class="inputLabel">받는이</span>
              <input type="text" name="recipient" />
            </div>
            <div class="rewardShippingItem">
              <span class="inputLabel">휴대폰 번호</span>
              <input type="text" name="phone" />
            </div>
            <div class="rewardShippingItem">
              <span class="inputLabel">배송 시 요청사항</span>
              <input type="text" name="message" placeholder="배송 전 연락주세요..." />
            </div>
            <div class="rewardShippingItem">
              <span class="inputLabel">주소</span>
              <button type="button" class="btnSubmit noFull" onclick="callAddress()">우편번호 검색</button>
              <span class="addressOverview" id="addressOverview"></span>
              <input type="hidden" name="zipCode" id="zipCode">
              <input type="hidden" name="address1" id="address1"/>
              <input type="text" name="address2" id="address2" placeholder="상세 주소" />
            </div>
          </div>
        </div>
      </div>
      <div class="rewardInner padding center">
        <button type="button" class="btnSubmit noFull" onclick="saveShippingLocation()">결제 요청</button>
      </div>
    </div>
  </form>
</article>
<div class="addressOverlay modalOverlay">
	<div id="addressWrap" class="modalInnerWrap"></div>
</div>
<div class="kakaoPayOverlay modalOverlay">
	<div id="kakaoPayWrap" class="modalInnerWrap">
		<iframe id="kakaoPayFrame"></iframe>
	</div>
</div>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function saveShippingLocation(){
	const url = cp + "/reward/save/rewardShippingLocation";
	const query = $("form[name=shippingLocationForm]").serialize();
	ajaxJSON(url, "post", query).then(function(data){
		if(data.result=="ok"){
			//저장된 거니까 pay 부르기...
			callKakaoPay("<%=cp%>/reward/${projectNo}/pay/kakao");
		}else{
			alert("배송지 저장 실패...");
		}
	}).catch(function(e){
		console.log(e);
		alert("배송지 저장 중 오류 발생");
	});
}
function callKakaoPay(url){
	//default kakao
	const $overlay = $(".kakaoPayOverlay");
	document.getElementById("kakaoPayFrame").src=url;
	$overlay.addClass("show");
}
$(function(){
	$("#kakaoPayFrame").on("load", function(e){
		const $overlay = $(".kakaoPayOverlay");
		try {
			const currentUrl = this.contentWindow.location.href;
			console.log(currentUrl);
			if(currentUrl.indexOf("/pay/kakao/success?pg_token=")>0){
				$overlay.removeClass("show");				
				location.href= cp+"/reward/pay/success";
			}
		} catch (e) {
			console.log(e);
		}
		  
	});
});
$(function(){
	 $(".modalOverlay").on("scroll touchmove mousewheel", function (e) {
		 e.preventDefault();
	});
});
</script>
<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_overlay = document.getElementsByClassName("addressOverlay")[0];
    var element_wrap = document.getElementById('addressWrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_overlay.style.display = "none";
        element_wrap.style.display = 'none';
    }

    function callAddress() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("address1").value = extraAddr;
                	
                } else {
                    document.getElementById("address2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipCode').value = data.zonecode;
                document.getElementById("address1").value = addr;
                document.getElementById("addressOverview").outerText = "("+ data.zonecode +") "+ addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address2").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_overlay.style.display = "none";
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_overlay.style.display = "block";
        element_wrap.style.display = 'block';
    }
</script>