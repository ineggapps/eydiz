<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
   String cp = request.getContextPath();
%>
<style>
	.awardsProjectName {
		display: inline-block;
		height: 48px;
	}
	
</style>

<article class="row">
<div class="contentWrapper">
          <article class="row">
            <div class="rowFull">
		        <div class="awardMaker100" style="text-align: center;">
		        	<p class = "awardContentTitle">이디즈 어워즈 15</p>
		        	<p class="awardContentTitleSub">2020 펀딩으로 트렌드를 이끈</p>
		        	<p class="awardContentTitleSub">3개 부분의 메이커/프로젝트 15을 소개합니다.</p>
		        </div>
            </div>
          </article>
          <article class="row">
            <div class="rowInner awardContentBody">
	           <div class = "awardCategory">
	            	<p>최고 펀딩액</p>
	            	<p class ="aCDetail">이디즈에서 펀딩액을 가장 많이 모은 프로젝트 Top 5</p>
	           </div>
	           <ul class="awardsGridContent">
	              <c:forEach var="bestSeller" items="${bestSellerList}" varStatus="status">
	              	<c:choose>
	              		<c:when test="${status.index==0 }">
	              			<ul class="awardsGridContent awardsNumberOne">	              		
		              			<li class="awardsItem" data-project-no="${bestSeller.projectNo}">
		              				<div class="awardsTop1"></div>
									<div class="awardsItemInner">
										<div class="thumbnail"
											style="background-image: url('${bestSeller.projectImageUrl}');">
											<span class="hidden">프로젝트 대표 이미지</span>
										</div>
										<div class="textWrap">
											<div class="subject">
												<span>${bestSeller.projectName}</span>
											</div>
											<ul class="desc">
												<li>
													<span class="name">${bestSeller.categoryName}</span>
												</li>
												<li>
													<span class="totalAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${bestSeller.totalAmount}" />원 달성</span>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
	              		</c:when>
	              		<c:otherwise>
		              		<li class="awardsItem" data-project-no="${bestSeller.projectNo}">
								<div class="awardsItemInner">
									<div class="thumbnail"
										style="background-image: url('${bestSeller.projectImageUrl}');">
										<span class="hidden">프로젝트 대표 이미지</span>
									</div>
									<div class="textWrap">
										<div class="subject">
											<span class="awardsProjectName">${bestSeller.projectName}</span>
										</div>
										<ul class="desc">
											<li>
												<span class="name">${bestSeller.categoryName}</span>
											</li>
											<li>
												<span class="totalAmount"><fmt:formatNumber type = "number" pattern = "#,###" value = "${bestSeller.totalAmount}" />원 달성</span>
											</li>
										</ul>
									</div>
								</div>
							</li>
	              		</c:otherwise>
	              	</c:choose>
	              </c:forEach>
	              </ul>
            </div>
            <div class="rowInner awardContentBody">
	           <div class = "awardCategory">
	            	<p>최대 달성률</p>
	            	<p class="aCDetail">달성률이 가장 높은 프로젝트 Top 5</p>
	           </div>
              <ul class="awardsGridContent">
	              <c:forEach var="highestList" items="${highestList}" varStatus="status">
	              	<c:choose>
	              		<c:when test="${status.index==0 }">
	              			<ul class="awardsGridContent awardsNumberOne">	              		
		              			<li class="awardsItem" data-project-no="${highestList.projectNo}">
		              				<div class="awardsTop1"></div>
									<div class="awardsItemInner">
										<div class="thumbnail"
											style="background-image: url('${highestList.projectImageUrl}');">
											<span class="hidden">프로젝트 대표 이미지</span>
										</div>
										<div class="textWrap">
											<div class="subject">
												<span>${highestList.projectName}</span>
											</div>
											<ul class="desc">
												<li>
													<span class="name">${highestList.categoryName}</span>
												</li>
												<li>
													<span class="totalAmount"><fmt:formatNumber type = "number" pattern = "###.#" value = "${highestList.percentage}" />%</span>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
	              		</c:when>
	              		<c:otherwise>
		              		<li class="awardsItem" data-project-no="${highestList.projectNo}">
								<div class="awardsItemInner">
									<div class="thumbnail"
										style="background-image: url('${highestList.projectImageUrl}');">
										<span class="hidden">프로젝트 대표 이미지</span>
									</div>
									<div class="textWrap">
										<div class="subject">
											<span class="awardsProjectName">${highestList.projectName}</span>
										</div>
										<ul class="desc">
											<li>
												<span class="name">${highestList.categoryName}</span>
											</li>
											<li>
												<span class="totalAmount"><fmt:formatNumber type = "number" pattern = "###.#" value = "${highestList.percentage}" />%</span>
											</li>
										</ul>
									</div>
								</div>
							</li>
	              		</c:otherwise>
	              	</c:choose>
	              </c:forEach>
	              </ul>
            </div>
            <div class="rowInner awardContentBody">
	           <div class = "awardCategory">
	            	<p>프로젝트 최다 보유</p>
	            	<p class="aCDetail">프로젝트를 가장 많이 보유한 브랜드 Top 5</p>
	           </div>
              <ul class="awardsGridContent">
	              <c:forEach var="brandRankList" items="${brandRankList}" varStatus="status">
	              	<c:choose>
	              		<c:when test="${status.index==0 }">
	              			<ul class="awardsGridContent awardsNumberOne">	              		
		              			<li class="awardsItem" data-brand-no="${brandRankList.brandNo}">
		              				<div class="awardsTop1"></div>
									<div class="awardsItemInner">
										<div class="thumbnail"
											style="background-image: url('${brandRankList.memberImageUrl}');">
											<span class="hidden">브랜드 대표 이미지</span>
										</div>
										<div class="textWrap">
											<div class="subject">
												<span>${brandRankList.brandName}</span>
											</div>
											<ul class="desc">
												<li>
													<span class="name">${brandRankList.managerName}</span>
												</li>
												<li>
													<span class="totalAmount">${brandRankList.projectAmount}개 보유중</span>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
	              		</c:when>
	              		<c:otherwise>
		              		<li class="awardsItem"  data-brand-no="${brandRankList.brandNo}">
								<div class="awardsItemInner">
									<div class="thumbnail"
										style="background-image: url('${brandRankList.memberImageUrl}');">
										<span class="hidden">프로젝트 대표 이미지</span>
									</div>
									<div class="textWrap">
										<div class="subject">
											<span class="awardsProjectName">${brandRankList.brandName}</span>
										</div>
										<ul class="desc">
											<li>
												<span class="name">${brandRankList.managerName}</span>
											</li>
											<li>
												<span class="totalAmount">${brandRankList.projectAmount}개 보유중</span>
											</li>
										</ul>
									</div>
								</div>
							</li>
	              		</c:otherwise>
	              	</c:choose>
	              </c:forEach>
	              </ul>
            </div>
          </article>
        </div>
</article>