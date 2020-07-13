<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>


<ul class="partnerGridContent">
<c:forEach var="dto" items="${plist}">
	<li class="partnerItem" onclick="location.href='<%=cp%>/detail/${dto.projectNo}'">
		<div class="partnerItemInner">
			<div class="thumbnail"
				style="background-image: url('${dto.projectImageUrl}');">
				<span class="hidden">드럼</span>
			</div>
			<div class="textWrap">
				<div class="subject">
					<a href="#"><span>${dto.projectName}</span></a>
				</div>
				<ul class="desc">
					<li><span class="category">${dto.parentCategoryNo == 1 ? "펀딩"  : "기부"}</span></li>
					<li><span class="name">${dto.categoryName}</span></li>
				</ul>
				<div class="status">
					<div class="progress">
						<div class="progressBar" style="width:${dto.percentage >= 100 ? '100' : dto.percentage }%;"></div>
					</div>
					<ul>
						<li><span class="percent">${dto.percentage}%</span>&centerdot;<span
							class="totalAmount">${dto.projectTotalAmount}원 달성</span>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</li>
</c:forEach>
</ul>
<div class = "partnerPaging">
	${pDataCount==0 ? "프로젝트가 존재하지 않습니다." : paging}
</div>