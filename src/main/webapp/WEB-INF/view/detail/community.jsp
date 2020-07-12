<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<c:if test="${not empty sessionScope.member}">
<div class="commentButtonController">
	<button type="button" class="btnGray per40" id="btnCommentModal">글 남기기</button>
</div>
<hr class="divider deeper" />
</c:if>
<!--커뮤니티 덧글 영역-->
<div class="commentWrap">
  <div class="parent commentItem hide">
    <div class="commentSide">
      <span class="parent commentAvatar"></span>
    </div>
    <div class="commentArea">
      <div class="commentHeader">
        <span class="parent author">아이디</span>
        <span class="parent date">작성일자</span>
        <span class="parent commentMenu"><span class="hidden">메뉴</span></span>
        <div class="parent commentContext hide">
        	<ul>
        		<li class="parent commentModify">수정</li>
        		<li class="parent commentDelete">삭제</li>
        	</ul>
        </div>
      </div>
      <div class="commentComponent">
	      <div class="parent commentContent">
	        내용
	      </div>
	       <div class="parent commentInputContent edit hide">
		        <textarea placeholder="수정할 댓글을 입력하세요" name="content" data-autoresize="true"></textarea>
		      	<div class="commentInputContentController">
		      		<button type="button" class="parent edit btnSubmit">수정</button>
		      	</div>
	    	</div>
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
            <c:if test="${sessionScope.member.memberImageUrl == null}">
		    <span class="reply commentAvatar">프로필사진</span>
		    </c:if>
		    <c:if test="${sessionScope.member.memberImageUrl != null}">
              <span class="reply commentAvatar" style="background-image:url('${sessionScope.member.memberImageUrl}')">프로필사진</span>
            </c:if>
        </div>
        <div class="commentArea">
          <div class="commentHeader">
            <span class="reply author">답글 작성자</span>
            <span class="reply date">답글 작성 시각</span>
            <span class="parent commentMenu"><span class="hidden">메뉴</span></span>
            <div class="parent commentContext edit hide">
	        	<ul>
	        		<li class="reply commentModify">수정</li>
	        		<li class="reply commentDelete">삭제</li>
	        	</ul>
	        </div>
          </div>
          <div class="commentComponent">
	          <div class="commentContent">
	            답글 내용
	          </div>
	          <div class="reply commentInputContent edit hide">
		          <textarea placeholder="수정할 답글을 입력하세요" name="content" data-autoresize="true"></textarea>
		        	<div class="commentInputContentController">
		        		<button type="button" class="reply edit btnSubmit">수정</button>
		        	</div>
		      </div>
          </div>
          
        </div>
      </div>
      </div>
      <c:if test="${not empty sessionScope.member }">
      <div class="commentInput reply">
        <div class="commentInputSide">
			<c:if test="${sessionScope.member.memberImageUrl == null}">
		    <span class="reply commentAvatar">프로필사진</span>
		    </c:if>
		    <c:if test="${sessionScope.member.memberImageUrl != null}">
              <span class="reply commentAvatar" style="background-image:url('${sessionScope.member.memberImageUrl}')">프로필사진</span>
            </c:if>        </div>
        <div class="commentInputContent">
	          <textarea placeholder="답글을 입력하세요" name="content" data-autoresize="true"></textarea>
	        	<div class="commentInputContentController">
	        		<button type="button" class="reply write btnSubmit">등록</button>
	        	</div>
        </div>
      </div>
      </c:if>
    </div>
  </div>
</div>
<div class="newsMore">
	<button type="button" class="btnNewsMore">더 보기<span class="bgMore">&nbsp;</span></button>
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