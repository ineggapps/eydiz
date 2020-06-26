<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
$(function(){
	$("#partnerStatusNo-5 span").addClass("on");
	listProjectPage(1);
	
	$(".detailNav a").click(function(){
		var statusNo = $(this).attr("data-pStatusNo");
		
		$(".detailNav a span").each(function(){
			$(this).removeClass("on");
		});
		
		$("#partnerStatusNo-"+statusNo+" span").addClass("on");
		
		listProjectPage(1);
	})
});



function ajaxHTML(url, method, query, selector) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		success:function(data) {
			if($.trim(data)=="error") {
				//listProjectPage(1);
				return false;
			}			
			$(selector).html(data);
		},
		beforeSend:function(jqXHR) {
			jqXHR.setRequestHeader("AJAX", true);
		},
		error:function(jqXHR) {
			if(jqXHR.state==403) {
				login();
				return false;
			}
			
			console.log(jqXHR.responseText);
		}
	});
}


// 프로젝트 리스트
function listProjectPage(page) {
	var brandNo = ${dto.brandNo};
	var url = "<%=cp%>/partner/articleList";
	var $tab = $(".detailNav a .on").parent();
	var statusNo = $tab.attr("data-pStatusNo");
	
	var query = "page="+page+"&brandNo="+brandNo+"&statusNo="+statusNo;

	ajaxHTML(url, "get", query, "#partnerArticleList");
}

function reloadPartnerProject() {
	 listProjectPage(1);
}


</script>

        <div class="contentWrapper">
          <article class="row">
	 	    <div class = "partnerArticleImage" style="background-image: url('${memberImageUrl}');" ></div>
            <div class="rowInner awardContentBody">
             <div class="pArticleName">
		        	<div class="pANWrap">
		        		<p class = "partnerContentText paTitle">${dto.brandName}</p>
		        	</div>
		      </div>
		      <div class = "pAResultBox">
		      	  <p class="pAResultTitle">프로젝트 현황</p>
		      	   <div class="pAResult1">
		      	   		<p>총 펀딩액</p>
		      	   		<span><fmt:formatNumber value="${brandTotalAmount}"/></span><span>원</span>
		      	   </div>
	              <ul>
	               <li class="item partnerResultCell">
	                  <div class="itemInner">
	                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-script-5-240.png');"></div>
	                      <div class="subject partnerResultText">
	                        <p>오픈한 프로젝트</p>
	                        <span class="partnerResultTextPoint">${brandTotalProject}</span><span> 건</span>
	                      </div>
	                  </div>
	                </li>
	               <li class="item partnerResultCell">
	                  <div class="itemInner">
	                    <div class="partnerResultIcon" style="background-image: url('../resource/images/partner/iconmonstr-party-21-120.png');"></div>
	                      <div class="subject partnerResultText">
	                        <p>참여한 투자자</p>
	                        <span class="partnerResultTextPoint"><fmt:formatNumber value="${brandTotalBuyMember}"/></span><span> 명</span>
	                      </div>
	                  </div>
	                </li>
	               <li class="item partnerResultCell partnerStandard">
	                  <p> * 이디즈 파트너의 총 합산 기록입니다. (${today})</p> <span><a href="<%=cp%>/partner/list?${query}">목록으로</a></span>
	                </li>
	              </ul>
		      </div>
            </div>
          </article>
          <article class="row partnerListContentBody">
	        <div class="rowInner">
	          <div class = "paListTitle">
	          	<p>참여프로젝트</p>
	          </div>
	         <nav class="row">
	            <div class="rowFull">
	              <ul class="= detailNav">
	                <li>
	                  <a id="partnerStatusNo-5" data-pStatusNo="5"><span>진행중</span></a>
	                </li>
	                <li>
	                  <a id="partnerStatusNo-6" data-pStatusNo="6"><span>종료</span></a>
	                </li>
	              </ul>
	            </div>
          </nav>
          	<div id = "partnerArticleList"></div>
            </div>
            </article>
        </div>
