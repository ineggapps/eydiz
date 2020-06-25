<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>
<ul class="projectList">
	<c:forEach var="item" items="${project}">
		<li data-project-no="${item.projectNo}">
			<div class="projectItem">
				<button type="button" class="btnDeleteProject"><span class="hidden">지우기</span></button>
				<div class="projectThumbnail">
					<div class="projectOverlay">
						<span class="whiteSubtitle">대표이미지 등록 필요</span>
					</div>
					<div class="projectBackground"
						style="
            background-image: url(${item.projectImageUrl});
          "></div>
				</div>
				<div class="projectDescription">
					<dl>
						<dt>${not empty item.projectName?item.projectName:"제목을 입력하세요"}</dt>
						<dd>${item.brandName}</dd>
					</dl>
					<div class="projectStatus">
						<c:if test="${item.statusNo==0}">
							<p>
								<span><i class="projectStatus ready"></i> 펀딩 준비 작성 중</span>
							</p>
						</c:if>
						<c:if test="${item.statusNo==1}">
							<p>
								<span><i class="projectStatus judge"></i> 펀딩 심사 중</span>
							</p>
						</c:if>
						<c:if test="${item.statusNo==2}">
							<p>
								<span><i class="projectStatus deny"></i> 반려</span>
							</p>
						</c:if>
						<c:if test="${item.statusNo==3}">
							<p>
								<span><i class="projectStatus report"></i> 신고접수된 프로젝트</span>
							</p>
						</c:if>
				        <c:if test="${item.statusNo==4}">        
						<p>
							<span><i class="projectStatus blind"></i> 블라인드</span>
						</p>
						</c:if>
						<c:if test="${item.statusNo==5}">
						<p>
							<span><i class="projectStatus activate"></i> 진행 중</span>
						</p>
						</c:if>
						<c:if test="${item.statusNo==6}">
						<p>
							<span><i class="projectStatus expire"></i> 반려</span>
						</p>
						</c:if>
					</div>
				</div>
				<div class="projectItemController">
					<a href="<%=cp %>/studio/project/${item.projectNo}/register" class="btnManage">스튜디오 바로가기</a>
				</div>
			</div>
		</li>
	</c:forEach>
</ul>