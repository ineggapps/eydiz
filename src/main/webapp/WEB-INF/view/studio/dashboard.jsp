<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <h2>대시보드</h2>
  <p class="description">프로젝트 기본 상태</p>
  <div class="infoItemWrap">
	<div class="projectStatus">
		<ul>
			<li ${project.statusNo==0?"class=\"on\"":""}>
				<div class="projectStatusItem">
					<h5>펀딩 준비 작성</h5>
					<p>펀딩 진행을 위해 프로젝트 내용을 작성하는 펀딩 준비 작성 단계입니다.</p>
				</div>
			</li>
			<li ${project.statusNo==2?"class=\"on\"":""}>
				<div class="projectStatusItem">
					<h5>반려</h5>
					<p>조건이 부합하지 않아 펀딩 내용을 보충하는 단계입니다.</p>
				</div>
			</li>
			<li ${project.statusNo==1?"class=\"on\"":""}>
				<div class="projectStatusItem">
					<h5>심사 중</h5>
					<p>펀딩 등록을 위해 관리자에게 승인을 기다리는 중입니다.</p>
				</div>
			</li>
			<li ${project.statusNo==5?"class=\"on\"":""}>
				<div class="projectStatusItem">
					<h5>활성</h5>
					<p>관리자 승인 이후 펀딩이 개시 전에 대기하거나 개시 중인 단계입니다.</p>
				</div>
			</li>
			<li ${project.statusNo==6?"class=\"on\"":""}>
				<div class="projectStatusItem">
					<h5>종료</h5>
					<p>프로젝트가 개시 종료된 상태입니다.</p>
				</div>
			</li>
		</ul>
	</div>
  </div>
</article>