<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">

function ajaxHTML(url, type, query, selector) {
	$.ajax({
		type:type
		,url:url
		,data:query
		,success:function(data) {
			if($.trim(data)=="error") {
				partnerBrandPage(1);
				return false;
			}	
			$(selector).html(data);
		}
		,beforeSend:function(jqXHR) {
	        jqXHR.setRequestHeader("AJAX", true);
	    }
	    ,error:function(jqXHR) {
	    	if(jqXHR.status==403) {
	    		login();
	    		return false;
	    	}
	    	console.log(jqXHR.responseText);
	    }
	});
}


function partnerBrandPage(page) {
	var url = "<%=cp%>/partner/brandList";
	var query = "page="+page;
	var search = $('form[name=partnerSearchForm]').serialize();
	query = query+"&"+search;
	var selector = "#partnerBrandListBox";
	
	ajaxHTML(url, "get", query, selector);
}

function searchList() {
	var f = document.partnerSearchForm;
	f.keyword.value = $.trim($("#partnerSearchKeyword").val());
	
	partnerBrandPage(1);
}

function reloadList(){
	var f = document.partnerSearchForm;
	f.keyword.value = "";
	
	partnerBrandPage(1);
}

$(function(){
	partnerBrandPage(1);
});

</script>


		<div class = "partnerBannerImage" >
        	<div class="awardsTitle">
        		<div class = "patnerBannerTitle">이디즈 파트너</div>
        		<div class = "TitleBannerSubText">
        		   <p>이디즈에서는 누구나 투자받고 투자하면서 함께 성장합니다.</p><br>
				   <p>1%의 특별한 소수가 아닌 99%의 다양한 사람들이 더불어 성장할 수 있는 곳,</p><br>
				   <p class="patnerBannerPoint">이디즈에서 다양한 분야에서 도전하는 메이커를 찾아보세요</p><br><br>
				</div>
        	</div>
        </div>
		<div class="rowFull">
			<article class="row">
		        <div class="partnerResult">
		        	<div class="pResultWrap">
		        		<p class = "partnerContentText prTitle">이디즈 파트너가 함께 만들어가는 성과</p>
		        		<p class="partnerContentText prContent">이디즈의 파트너는 펀딩형 크라우드 펀딩에 도전하고 있으며, 괄목할 만한 성과를 만들어가고 있습니다.</p>
		        	</div>
		        </div>
          	</article>
          </div>
          <article class="row">
            <div class="rowInner awardContentBody">
              <ul>
               <li class="item partnerResultCell">
                  <div class="itemInner">
   					  <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-handshake-7-240.png');"></div>
                      <div class="subject partnerResultText">
                        <p>이디즈와 함께한 파트너</p>
                        <span class="partnerResultTextPoint">${totalBrandCount}</span><span> 브랜드</span>
                      </div>
                  </div>
                </li>
               <li class="item partnerResultCell">
                  <div class="itemInner">
                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-script-5-240.png');"></div>
                      <div class="subject partnerResultText">
                        <p>파트너가 진행한 프로젝트</p>
                        <span class="partnerResultTextPoint">${totalProjectCount}</span><span> 건</span>
                      </div>
                  </div>
                </li>
               <li class="item partnerResultCell">
                  <div class="itemInner">
                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-party-21-120.png');"></div>
                      <div class="subject partnerResultText">
                        <p>프로젝트에 참여한 투자자</p>
                        <span class="partnerResultTextPoint">${totalBuyMemberCount}</span><span> 명</span>
                      </div>
                  </div>
                </li>
               <li class="item partnerResultCell partnerStandard">
                  <p> * 이디즈 파트너의 총 합산 기록입니다. (${today})</p>
                </li>
              </ul>
            </div>
          </article>
          <article class="row partnerListContentBody">
	        <div class="rowInner">
	          <div class = "partnerListTitle">
	          	<p>이디즈와 함께하는 사람들</p>
	          </div>
              <div id="partnerBrandListBox"></div>
            </div>
            </article>
            <div class = "partnerMakePJ">
          	<p>이디즈에서는 누구나</p>
          	<p>성공의 주인공이 될 수 있습니다.</p>
          	<p>지금 바로 펀딩에 도전하세요!</p>
   			</div>
