<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
$(function(){
	var today="${today}";
	var date="${date}";
	$(".schoolcalender .textDate").each(function (i) {
        var s=$(this).attr("data-date");
        if(s==date) {
        	$(this).parent().css("background", "#e4e4e4");
        }
        if(s==date) {
        	$(this).css("font-weight", "600");
        }
    });
});

$(function(){
	$(".contentBar .barselect").click(function(){
		var sccaNo = $(this).attr("data-sccaNo");
		var url="<%=cp%>/school/main?sccaNo="+sccaNo;
		location.href=url;
	});
});

function changeDate(date) {
	var url="<%=cp%>/school/main?date="+date;
	var sccaNo = "${sccaNo}";
	if(sccaNo!="0") {
		url+="&sccaNo="+sccaNo;
	}
	location.href=url;
}

$(function(){
	$(".smallCalendar .textDate").click(function(){
		var date = $(this).attr("data-date");
		changeDate(date);
	});
});

</script>

<article class="row">
	<div class="schoolForm">
		<div class="schoolimage">
			<div class="imageindex">
				<div class="schoolimginto">
					<h2>eydiz School</h2>
					<p>이디즈 스쿨은 최고의 크라우드펀딩 콘텐츠를 개발하고 전파합니다.</p>
				</div>
			</div>
		</div>
	</div>
	<div class="schoolcontent">
		<h2>이디즈스쿨</h2>
		<p>이디즈스쿨은 국내 최고의 크라우드 펀딩 전문가 그룹이 약 3,000여개의 펀딩 사례를 연구 및 분석한 결과를 바탕으로 예비 메이커에게 온.오프라인 교육 서비스를 제공합니다.</p>
	</div>
	<div class="scontent">
		<h2>이디즈스쿨 온라인 강의</h2>
		<p>이디즈펀딩의 핵심적인 기본개념을 온라인 강의를 통해 쉽고 빠르게 이해할 수 있습니다.</p>
	</div>
	<div class="contentBar">
		<ul>
			<li class="bartitle">${month}월  모집중인 이디즈스쿨 강의 리스트</li>
			<li class="barselect ${sccaNo==0?'select-menu':'' }" data-sccaNo="0" >전체 보기</li>
			<li class="barselect ${sccaNo==1?'select-menu':'' }" data-sccaNo="1" >입문</li>
			<li class="barselect ${sccaNo==2?'select-menu':'' }" data-sccaNo="2" >실전</li>
			<li class="barselect ${sccaNo==3?'select-menu':'' }" data-sccaNo="3" >멘토링</li>
			<li class="barselect ${sccaNo==4?'select-menu':'' }" data-sccaNo="4" >특강</li>
		</ul>
	</div>
	<div class="slistForm">
			<div class="schoollist-content">
				<c:forEach var="dto" items="${list}">
					<div class="schoollist">
						<ul>
							<li class="leftlist"><a href="<%=cp%>/school/article?scNo=${dto.scNo}&sccaNo=${sccaNo}&date=${date}">${dto.scSubject}</a></li>
						</ul>
						<ul>
							<li class="sccaN">${dto.sccaName}</li>
							<li class="sccaS"><a href="<%=cp%>/school/article?scNo=${dto.scNo}&sccaNo=${sccaNo}&date=${date}"> &nbsp;&nbsp; [${dto.sccaName}] &nbsp; ${dto.scSubject}</a></li>
							<li class="sccaC"> &nbsp;&nbsp; ${dto.scContent}</li>
							<li>&nbsp;&nbsp; 일시 :  ${dto.scDate}</li>
							<li class="scAllCount"> ${dto.scAllNumber} 명</li>
							<li class="scCount">  ${dto.schoolProject} / &nbsp;</li>
						</ul>
					</div>
				</c:forEach>
				
				<c:if test="${list.size()==0}">
					<div class="schoollist2">
						<ul>
							<li>등록된 게시물이 없습니다...</li>
						</ul>
					</div>
				</c:if>
			</div>
		<div class="schoolcalender" style="display: inline-block; vertical-align: top;">
			<table style="width: 100%; border-spacing: 0;" >
				  <tr height="35">
				   	<td align="center">
				   		<span class="btnDate" onclick="changeDate('${preMonth}');">＜</span>
				   		<span class="titleDate">${year}년 ${month}월</span>
				   		<span class="btnDate" onclick="changeDate('${nextMonth}');">＞</span>
				   		<span class="btnDate" onclick="changeDate('${today}');">오늘</span>
				   	</td>
				  </tr>
			</table>
			
			<table class="smallCalendar" style="width: 100%; margin-top:5px; border-spacing: 1px;  " >
				<tr align="center" height="40" bgcolor="#ffffff">
					<td width="40" style="color:#ff0000;">일</td>
					<td width="40">월</td>
					<td width="40">화</td>
					<td width="40">수</td>
					<td width="40">목</td>
					<td width="40">금</td>
					<td width="40" style="color:#0000ff;">토</td>
				</tr>
						   		
			    <c:forEach var="row" items="${days}" >
					<tr align="left" height="40" bgcolor="#ffffff">
						<c:forEach var="d" items="${row}">
							<td align="center" class="tdDay" valign="middle">
								${d}
							</td>
						</c:forEach>
					</tr>
			    </c:forEach>
			</table>			
		</div>
		<c:if test="${sessionScope.member.memberNo==1 || sessionScope.member.memberNo==dto.memberNo}">
			<div>
				<button class="schoolbtn" type="button" onclick="javascript:location.href='<%=cp%>/school/createschool'">등록하기</button>
		   	</div>
		</c:if>
	</div>
</article>