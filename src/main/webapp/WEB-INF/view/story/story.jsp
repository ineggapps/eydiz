<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript" src="<%=cp%>/resource/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	var storyCnum="${storyCnum}";
	$("#storyItemMenu"+storyCnum).addClass("on");
	
});

function check() {
	if (! document.storyForm.storyContent.value) {
        alert("내용을 입력해주세요");
        return;
	}
	document.storyForm.submit();
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
		var url="<%=cp%>/story/insertStoryLike";
		var storyNum=$(this).attr("data-storyNum");
		var query = {storyNum:storyNum};
		var fn = function(data){
			var state=data.state;
			if(state=="true") {
				var count = data.storyLikeCount;
				$(".likecount").text(count);
			} else if(state=="false") {
				alert("이미 좋아요한 게시물입니다.");
			}
		};
		
		ajaxJSON(url, "post", query, fn);
	});
});

function deleteStory(storyNum, id) {
	 	if(id=='${sessionScope.member.memberId}' || '${sessionScope.member.memberId}'=='eydiz'){
	 		var url = "<%=cp%>/story/delete?storyNum="+storyNum;
			
			if(confirm("게시물을 삭제하시겠습니까?")) {
				location.href=url;
			}
	 	}
}

</script>

<article class="row">
 <div class="rowInner">
              <ul class="categoryContenta">
                <li style="background-image: url('https://cdn.pixabay.com/photo/2020/06/07/13/21/toronto-5270413_960_720.jpg');">
                  <a>e &nbsp;&nbsp; y &nbsp;&nbsp; d &nbsp;&nbsp; i &nbsp;&nbsp; z &nbsp;&nbsp; : &nbsp;&nbsp; s &nbsp;&nbsp; t &nbsp;&nbsp; o &nbsp;&nbsp; r &nbsp;&nbsp; y</a>
                </li>
               </ul>
            </div>
          </article>
        <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li id="storyItemMenu1">
                  <a href="<%=cp%>/story/story?storyCnum=1"><span>펀딩 후기</span></a>
                </li>
                <li id="storyItemMenu2">
                  <a href="<%=cp%>/story/story?storyCnum=2"><span>기부 후기</span></a>
                </li>
                <li id="storyItemMenu3">
                  <a href="<%=cp%>/story/story?storyCnum=3"><span>프로젝트 후기</span><span class="badge"></span></a>
                </li>
              </ul>
            </div>
          </nav>
          
		 <article class="gridContent2">
            <div class="rowInner">
              <div class="s_titleBar">
                <div><span class="title_s">${storyCnum=="1"?"펀딩 후기":(storyCnum=="2"?"기부 후기":"프로젝트 후기")}</span></div>
       		  </div>
       		
       		<form name="storyForm" method="post" action="<%=cp%>/story/insert" onsubmit="return submitContents(this);" style="margin-left: 30px;">
    		<div class="write">
                 <div class="write2">
                         <span class="aaa">${storyCnum=="1"?"펀딩 후기":(storyCnum=="2"?"기부 후기":"프로젝트 후기")} 작성하기 </span>
                 </div>
                 <div class="writebox">
                       <textarea name="storyContent" id="content" class="boxTF" rows="3" style="width: 95%; height: 100px;"></textarea>
                  </div>
                  <div class="btnbox2">
                  	   <input type="hidden" name="storyCnum" value="${storyCnum}">
                       <button type="submit" class="btn"> 등록하기 </button>
                  </div>           
            </div>
            
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "<%=cp%>/resource/se/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["content"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
	
	try {
		// elClickedObj.form.submit();
		return check();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '돋움';
	var nFontSize = 24;
	oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>            
            </form>
         
 			<ul class="gridContent2">
                <li class="item2" >
                  <div class="itemInner2" align="center">
     				<c:forEach var="dto" items="${list}">
                    <div>
                      <span class="hidden">내용</span>
                    </div>
                    <div class="textWrap2">
                      <div class="subject2">
                        <a href="#"><span class="subject_s">${dto.stroyCname}</span></a>
                      </div>
                      <div class="www">
                      	<span>${dto.memberId}</span>
                      </div>
                      <div class="bbox">
                      	<span class="bbb">
                      		${dto.storyContent}
						</span>
                      </div>
                      
                    <div class="likebox">
					 <button type="button" class="like" data-storyNum="${dto.storyNum} ">
						<span class="likecount">♥</span>
					 </button>
				    </div>
                      
                     <div align="right">
                      <ul class="s_desc">
                        <li><span> ${dto.memberId} </span></li>
                        <li><span> ${dto.storyCreated} </span></li>
                        <li><span>${dto.storyLikeCount}</span></li>
                        <li>
                         <c:if test="${sessionScope.member.memberId==dto.memberId || sessionScope.member.memberId=='eydiz'}">		    
			          		<button type="button" class="dbtn" onclick="deleteStory('${dto.storyNum}', '${dto.memberId }');">삭제</button>
			      		 </c:if>
                        </li>
                      </ul>
                      </div>
                    </div>
                    </c:forEach>
                  </div>
                </li>
	          </ul>
	       
	      
       		</div>
          
</article>