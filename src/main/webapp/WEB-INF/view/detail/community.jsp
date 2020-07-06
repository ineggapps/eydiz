<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="commentButtonController">
	<button type="button" class="btnGray per40" id="btnCommentModal">글 남기기</button>
</div>
<hr class="divider deeper" />
<!--커뮤니티 덧글 영역-->
<div class="commentWrap">
  <div class="parent commentItem hide">
    <div class="commentSide">
      <span class="parent commentAvatar"></span>
    </div>
    <div class="commentArea">
      <div class="commentHeader">
        <span class="parent author">${item.memberId}</span>
        <span class="parent date">${item.createdDate}</span>
      </div>
      <div class="parent commentContent">
        ${item.content}
      </div>
      <div class="commentButtonReply">
        <button
          type="button"
          class="btnReply"
        >
          답글
        </button>
      </div>
      <div class="commentReplyWrap">
      <div class="commentItem reply hide">
        <div class="commentSide">
          <span class="reply commentAvatar">프로필사진</span>
        </div>
        <div class="commentArea">
          <div class="commentHeader">
            <span class="reply author">또치</span>
            <span class="reply date">2020-09-00</span>
          </div>
          <div class="commentContent">
            답글 내용
          </div>
        </div>
      </div>
      </div>
      <div class="commentInput reply">
        <div class="commentInputSide">
          <span class="reply commentAvatar">프로필사진</span>
        </div>
        <div class="commentInputContent">
	          <textarea placeholder="답글을 입력하세요" name="content" data-autoresize="true"></textarea>
	        	<div class="commentInputContentController">
	        		<button type="button" class="reply btnSubmit">등록</button>
	        	</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--커뮤니티 덧글 영역 끝-->
<div class="modalOverlay">
	<div class="modal">
		<form name="modalForm">
		<input type="hidden" name="projectNo" value="${projectNo}"/>
		<div class="modalContentWrap">
			<a href="#" class="btnClose"><span class="hidden">닫기</span></a>
			<div class="modalContentHeader">
				<h2>덧글 남기기</h2>
			</div>
			<div class="modalContentArea">
				<textarea name="content" placeholder="브랜드에게 응원, 의견, 체험 등의 리뷰 메시지를 남겨주세요!"></textarea>
			</div>
			<div class="modalWarning">
				<p>최근 브랜드에게 또는 제3자에게 허위사실 유포하거나 비방하는 댓글로 인해 당사자 간에 법적분쟁이 발생하고 있습니다. 악의적 댓을 작성하면 명예훼손, 모욕 등 법적으로 책임을 질 수 있습니다.</p>
			</div>
			<div class="modalController">
				<button class="comment btnSubmit" type="button">등록</button>
			</div>
		</div>
		</form>
	</div>
</div>
<script src="<%=cp%>/resource/js/detail/community.js"></script>