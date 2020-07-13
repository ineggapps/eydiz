<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script type="text/javascript">
function updateProjectNews(newsNo) {
	var q = "newsNo=${dto.newsNo}&${query}";
	var url = "<%=cp%>/studio/project/${projectNo}/news/update?"+q;
	
	location.href=url;
}
</script>

<article class="row">
  <h2>기본 정보</h2>
  <p class="description">프로젝트 새소식 내용을 확인하세요.</p>
  <div class="infoItemWrap">
	<form name="projectForm" action="<%=cp %>/studio/project/${projectNo}/news/read" method="post" onsubmit="return submitContents(this)">
    <div class="infoItem required">
      <div class="infoTitle"><span>새소식 제목</span></div>
      <p class="desc">프로젝트 새소식 제목입니다</p>
      <div class="inputWrap">
        <p class="studionewstitle">${dto.title}</p>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>프로젝트 새소식 내용입니다</span></div>
      <div class="inputWrap">
        <div class="studionewstitle"><p>${dto.content}</p></div>
      </div>
    </div>
	<button type="button" class="btnSubmit" id="btnSave" onclick="updateProjectNews('${dto.newsNo}');">수정하기</button>
  </form>
  </div>
</article>