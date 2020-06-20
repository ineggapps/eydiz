<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
  <h2>기본 정보</h2>
  <p class="description">프로젝트를 소개할 기본 정보를 입력하세요.</p>
  <div class="infoItemWrap">
	<form name="projectForm" action="." method="post" enctype="multipart/form-data">
	<input type="hidden" name="projectNo" value="${project.projectNo}"/>
    <div class="infoItem required">
      <div class="infoTitle"><span>프로젝트 이름</span></div>
      <p class="desc">프로젝트 이름을 입력하세요</p>
      <div class="inputWrap">
        <input type="text" id="projectName" name="projectName" value="${project.projectName}" />
      </div>
    </div>

    <div class="infoItem">
      <div class="infoTitle"><span>프로젝트 진행상황</span></div>
      <p class="desc">심사관이 평가할 수 있도록 프로젝트 진행상황에 대해 짤막하게 메모를 입력하세요.</p>
      <div class="inputWrap">
        <input type="text" id="projectStatusMemo" name="projectStatusMemo" value="${project.projectStatusMemo}" />
      </div>
    </div>
    
    
    <div class="infoItem">
      <div class="infoTitle"><span>목표 금액</span></div>
      <p class="desc">50만 원에서 1억 원까지 설정할 수 있습니다.</p>
      <div class="inputWrap">
        <input type="text" id="projectGoalAmount" name="projectGoalAmount" value="${project.projectGoalAmount}"/>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>대표 이미지</span></div>
      <div class="inputWrap">
        <input type="file" name="uploadImage" id="uploadImage" class="hide" />
        <div class="imageBox" ${not empty project.projectImageUrl?"data-show=\"true\"":"data-show=\"false\""} style="background-image:url(${project.projectImageUrl})">
        	<button type="button" id="btnDeleteImage" id="btnDeleteImage"><span class="hidden">지우기</span></button>
        </div>
        <div class="uploadBox">        
        <button type="button" id="btnProjectImage" class="btnDarkContrast">
          <span class="iconCamera">&nbsp;</span>등록하기
        </button>
	     <p class="desc">
	        3MB 이하의 JPEG, PNG 파일 <br />
	        사이즈 : 1200X675 픽셀 이상<br />
	        텍스트 및 로고 삽입 금지<br />
	     </p>
        </div>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>카테고리</span></div>
      <div class="inputWrap">
        <select name="categoryNo">
          <option>::선택::</option>
          <c:forEach var="item" items="${category}">
          <option value="${item.categoryNo}" ${item.categoryNo==project.categoryNo?"selected=\"selected\"":""}>${item.categoryName}</option>
          </c:forEach>
        </select>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>프로젝트 시작일</span></div>
      <p class="desc">리워드를 개시하기 위해 프로젝트 시작일을 선택하세요.</p>
      <div class="inputWrap">
        <input type="text" id="projectStartDate" name="projectStartDate" value="${project.projectStartDate}" />
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>프로젝트 종료일</span></div>
      <p class="desc">리워드를 마감하기 위한 프로젝트 종료일을 선택하세요.</p>
      <div class="inputWrap">
        <input type="text" id="projectEndDate" name="projectEndDate" value="${project.projectEndDate}"/>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>검색용 태그</span></div>
      <p class="desc">엔터를 누르면 태그가 등록됩니다. (최대 10개까지 입력 가능)</p>
      <div class="inputWrap">
        <input type="text" id="keyword" name="keyword" />
      </div>
    </div>

    <div class="infoItem" id="tags">
      <div class="infoTitle primary small strong" id="hashTitle">
        0/10개의 해시 태그
      </div>
      <ul class="hashItems">
        <li class="template">
          <span class="text">#안녕</span
          ><a href="#" class="btnHashDelete"
            ><span class="hidden">해시태그 삭제</span></a
          >
        </li>
      </ul>
    </div>
  </form>
  </div>
  <button type="submit" class="btnSubmit" id="btnSave">저장하기</button>
  <script type="text/javascript" src="<%=cp%>/resource/js/studio/project_register.js"></script>
</article>