<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
$(function() {
	$("#mate-1").addClass("buttonOn");
	listPage(1);
	
	$(".matebtn").click(function() {
		var fmcaNo = $(this).attr("data-fmcaNo");
		
		$(".matebtn").each(function() {
			$(this).removeClass("buttonOn");
		});
		
		$("#mate-"+fmcaNo).addClass("buttonOn");

		listPage(1);
	});
});

function ajaxJSON(url, method, query, fn) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		dataType:"json",
		success:function(data) {
			fn(data);
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

function ajaxHTML(url, method, query, selector) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		success:function(data) {
			if($.trim(data)=="error") {
				listPage(1);
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

//글 리스트
function listPage(page) {
	var url="<%=cp%>/mate/list";
	var $tab = $(".matebtnLayout .buttonOn");
	var fmcaNo = $tab.attr("data-fmcaNo");

	var query="pageNo="+page+"&fmcaNo="+fmcaNo;
	
	ajaxHTML(url, "get", query, "#mateContentLayout");
}

function contentView(fmNo, page) {

	var $tab = $(".matebtnLayout .buttonOn");
	var fmcaNo = $tab.attr("data-fmcaNo");
	
	var url="<%=cp%>/mate/article";
	var query="fmNo="+fmNo+"&fmcaNo="+fmcaNo;
	
	query=query+"&pageNo="+page;

	location.href=url+"?"+query;
}

</script>

<article class="row">
      	<div class="matebody">
      		<div class="peopleimg">
      			<div class="img-cover">
	      			<div class="imginto">
	      				<h2>펀딩메이트란?</h2>
	      				<br><br><br><br><br><br><br>
	      				<p>와디즈 펀딩 오픈에 대한 파트너를 서로 찾을 수 있게 도와 주는 서비스 입니다.</p>
	      				<p>펀딩메이트를 통해 다양한 프로젝트를 진행 경험이 있는 파트너와 쉽고 간편하게 연결 될 수 있습니다.</p>
	      			</div>
	      			<div>
	      				<button type="button" onclick="javascript:location.href='<%=cp%>/mate/created'">등록하기</button>
	      			</div>
      			</div>
			</div>
			<br><br><br><br><br>
			<div class="mateguide">
				<h2>이용방법</h2>
				<p>1. 원하는 서비스 분야를 선택하세요.</p>
				<p>2. 다양한 펀딩메이트의 정보를 꼼꼼히 확인하세요.</p>
				<p>3. 펀딩메이트를 확인 후 가입하세요.</p>
				<br><br><br>
				<h2>유의사항</h2>
				<p>이디즈는 메이커의 편의를 위하여 필요한 서비스를 단순 지원 합니다. 이디즈는 펀딩
				메이트와 메이커 간 거래 관계에는 일체 관여하지 않으며, 서비스 품질 및 거래에 대한 책임은 각 펀딩 메이트에게 있습니다.</p>
			</div>
			<div class="matebtnLayout">
				<ul>
					<li>
						<button class="mbtn hover1 matebtn" id="mate-1" data-fmcaNo="1">전체</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn hover1 matebtn" id="mate-2" data-fmcaNo="2">펀딩/물품</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn hover1 matebtn" id="mate-3" data-fmcaNo="3">펀딩/공연</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn hover1 matebtn" id="mate-4" data-fmcaNo="4">펀딩/음악</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn hover1 matebtn" id="mate-5" data-fmcaNo="5">기부</button>
					</li>
				</ul>
			</div>
			<div id="mateContentLayout"></div>
      	</div>
</article>