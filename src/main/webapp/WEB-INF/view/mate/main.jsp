<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

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
			<div class="matebtn">
				<ul>
					<li>
						<button class="mbtn">전체</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn">펀딩/물품</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn">펀딩/공연</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn">펀딩/음악</button>&nbsp;&nbsp;&nbsp;
						<button class="mbtn">기부</button>
					</li>
				</ul>
			</div>
			<c:forEach var="dto" items="${list}">
			<div class="mateForm">
				<div class="matecontent">
					<ul>
						<li class="mcontents">비쥬얼메이커</li>
						<li class="mcontentc">기획부터 마케팅 까지 분야별 전문가들이 모여 기업에 딱 맞는 컨텐츠를 연구하고</li>
						<li class="mcontent"><a href="#"> 더 보기 > </a></li>
						<li class="mcount">(1/30)명</li>
					</ul>
				</div>
			</div> 
			</c:forEach>      	
      	</div>
</article>