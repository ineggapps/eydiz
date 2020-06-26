<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>


<script type="text/javascript">
function deleteCast(castNum, id) {
	<c:if test="${sessionScope.member.memberId=='eydiz'}">
		var q = "castNum=${dto.castNum}&${query}";
		var url = "<%=cp%>/cast/delete?"+q;
		
		if(confirm("이 게시물을 삭제 하시겠습니까 ? ")) {
				location.href=url;
		}   
	</c:if>    
	<c:if test="${sessionScope.member.memberId!='eydiz'}">
		alert("게시물을 삭제할 수  없습니다.");
	</c:if>
	}

	function updateCast(castNum, id) {
	<c:if test="${sessionScope.member.memberId=='eydiz'}">
		var q = "castNum=${dto.castNum}&${query}";
		var url = "<%=cp%>/cast/update?"+q;

		location.href=url;
	</c:if>
	<c:if test="${sessionScope.member.memberId!='eydiz'}">
		alert("게시물을 수정할 수  없습니다.");
	</c:if>

}
	
function ajaxJSON(url, type, query, fn) {
	$.ajax({
		type:type
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data) {
			fn(data);
		}
		,beforeSend:function(jqXHR) {
	       jqXHR.setRequestHeader("AJAX", true);
	    }
		   ,error:function(jqXHR) {
		   	if(jqXHR.status==403) {
		   		login();
		  		return false;
		   	}
		   	console.log(jqXHR.responseText);
		   }
	});
}


$(function(){
	$(".like").click(function(){
		var url="<%=cp%>/cast/insertCastLike";
		var castNum=$(this).attr("data-castNum");
		var query = {castNum:castNum};
		alert(castNum);
		var fn = function(data){
			var state=data.state;
			if(state=="true") {
				var count = data.castLikeCount;
				$(".likecount").text(count);
			} else if(state=="false") {
				alert("이미 좋아요한 게시물입니다.");
			}
		};
			
		ajaxJSON(url, "post", query, fn);
	});
});

</script>

     		 <div class="rcontent">
				<div class="section">
    					<div class="info">
	    					<div class="story-info">
	    						<p class="board">이디즈 캐스트</p>
								<p class="title"> ${dto.castTitle} </p>
								<p class="rlxk"><em class="editor">${sessionScope.member.memberId}</em><em class="date">${dto.castCreated}</em><em class="like">♥</em></p>
								
							</div>
    					</div>
    					<div class="detail-content">
    						<div class="inner-content">
    							<div><img src="https://images.pexels.com/photos/3184465/pexels-photo-3184465.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500" class="img"></div>
    							<p>${dto.castContent}</p>
    						</div>
    					</div>
    			</div>
				
				<div class="likebox">
					<button type="button" class="like" data-castNum="${dto.castNum}">
						<span class="likecount"> ♥ </span>
					</button>
				</div>
				
				<table style="width: 100%; margin: 0px auto 20px; border-spacing: 0px;">
				<tr height="45">
			    <td width="300" align="left">
			       <c:if test="${sessionScope.member.memberId=='eydiz'}">				    
			          <button type="button" class="btn" onclick="updateCast('${dto.castNum}', '${dto.memberId }');">수정</button>
			       </c:if>
			       <c:if test="${sessionScope.member.memberId=='eydiz'}">				    
			          <button type="button" class="btn" onclick="deleteCast('${dto.castNum}', '${dto.memberId }');">삭제</button>
			       </c:if>
			    </td>
			    </tr>
			    </table>

				<div class="comment">
                            <div class="msgbox" style="margin-top:0">
                              <p>와디즈 서비스의 건전한 운영을 위하여 운영기준 상 문제의 소지가 있거나 게시물에 관련이 없는 악의적인 댓글은 임의로 삭제될 수 있습니다.</p>
                            </div>
		    				<div class="main">
			    				<textarea placeholder="댓글을 입력하세요."></textarea>
			    				<div class="btnbox">
			    					<button type="button" class="btn">댓글달기</button>
			    				</div>
		    				</div>
		    				
		    				<div id="listComment"></div>
		    	</div>
		    	</div>