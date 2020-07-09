<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<script>
$(function(){
	$("input#avatar").change(function(e){
		const url = "<%=cp%>/mypage/upload/avatar";
		const formData = new FormData();
		console.log(document.getElementsByName('avatar')[0]);
		formData.append('avatar', document.getElementsByName('avatar')[0].files[0])
		ajaxJSONFile(url, "post", formData).then(function(data){
			$("div.avatar").css("background-image", "url('"+data.avatar_uri+"')");
			$("span.displayAvatar").css("background-image", "url('"+data.avatar_uri+"')");
		}).catch(function(e){
			console.log(e);			
		});
	});
});
</script>
<article class="row">
  <form action="<%=cp %>/mypage/myInfo" method="post" name="myInfoForm">
    <h2>개인정보 수정</h2>
    <p class="description">
      안녕하세요. 개인정보를 확인하거나 수정하세요.
    </p>

    <div class="infoItemWrap">
      
      <div class="infoItem">
        <div class="infoTitle"><span>아이디</span></div>
        <p class="desc"></p>
        <div class="inputWrap">
          ${member.memberId}
        </div>
      </div>

      <div class="infoItem required">
        <div class="infoTitle"><span>프로필 사진</span></div>
        <div class="inputWrap">
        	<c:if test="${member.memberImageUrl != null }">
        	<div 
        		class="avatar"
        		style="background-image:url('${member.memberImageUrl}')"
        	>&nbsp;</div>
        	</c:if>
        	<c:if test="${member.memberImageUrl == null }">
        	<div 
        		class="avatar"
        	>&nbsp;</div>
        	</c:if>
        	<button type="button" class="btnSubmit avatar" onclick="javascript:$('#avatar').click()">편집</button>
          <div class="hide">
          	<input type="file" name="avatar" id="avatar"/>
          </div>
        </div>
        <p class="desc">자신의 프로필 사진을 등록해 보세요!</p>
      </div>
      
      <div class="infoItem required">
        <div class="infoTitle"><span>닉네임</span></div>
        <p class="desc"></p>
        <div class="inputWrap">
          <input type="text" id="memberNickname" name="memberNickname" value="${member.memberNickname}" />
        </div>
      </div>

      <div class="infoItem required">
        <div class="infoTitle"><span>이메일</span></div>
        <p class="desc">이메일 주소를 입력하세요.</p>
        <div class="inputWrap">
          <input type="text" id="memberEmail" name="memberEmail" value="${member.memberEmail}" />
        </div>
      </div>

    </div>
    <div class="submitContainer">
      <button type="submit" class="btnSubmit width60per">수정하기</button>
    </div>
  </form>
</article>
