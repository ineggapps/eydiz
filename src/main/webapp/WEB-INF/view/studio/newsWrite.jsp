<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>


<article class="row">
  <h2>기본 정보</h2>
  <p class="description">프로젝트 새소식을 입력하세요.</p>
  <div class="infoItemWrap">
	<form name="projectForm" action="<%=cp %>/studio/project/${projectNo}/news/${mode}" method="post" onsubmit="return submitContents(this)">
	<input type="hidden" name="projectNo" value="${projectNo}"/>
    <div class="infoItem required">
      <div class="infoTitle"><span>새소식 제목</span></div>
      <p class="desc">프로젝트 새소식 제목을 입력해 보세요</p>
      <div class="inputWrap">
        <input type="text" id="projectSummary" name="title" value="${dto.title}"/>
      </div>
    </div>

    <div class="infoItem required">
      <div class="infoTitle"><span>프로젝트 새소식 작성하기</span></div>
      <div class="inputWrap">
        <textarea id="projectStory" name="content">${dto.content}</textarea>
      </div>
    </div>
	
	<button type="submit" class="btnSubmit" id="btnSave">${mode == 'update'?'수정하기':'저장하기'}</button>
  </form>
  </div>
	<script type="text/javascript" src="<%=cp %>/resource/se/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
		 oAppRef: oEditors,
		 elPlaceHolder: "projectStory",
		 sSkinURI: "<%=cp%>/resource/se/SmartEditor2Skin.html",
		 fCreator: "createSEditor2"
		});
		// ‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
		function submitContents(elClickedObj) {
		 // 에디터의 내용이 textarea에 적용된다.
		 oEditors.getById["projectStory"].exec("UPDATE_CONTENTS_FIELD", []);

		 // 에디터의 내용에 대한 값 검증은 이곳에서
		 // document.getElementById("ir1").value를 이용해서 처리한다.
			 try {
			     elClickedObj.form.submit();
			 } catch(e) {} 
		}
		 
	</script>
</article>