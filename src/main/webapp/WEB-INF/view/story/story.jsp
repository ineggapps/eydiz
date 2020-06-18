<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
function sendStory() {
    var f = document.storyForm;

	var str = f.storyTitle.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.storyTitle.focus();
        return;
    }

	str = f.storyContent.value;
    if(!str) {
        alert("내용을 입력하세요. ");
        f.storyContent.focus();
        return;
    }

	f.action="<%=cp%>/story/story";

    f.submit();
}
</script>

<article class="row">
 <div class="rowInner">
              <ul class="categoryContent">
                <li style="background-image: url('https://cdn.pixabay.com/photo/2020/06/07/13/21/toronto-5270413_960_720.jpg');">
                  <a> w &nbsp; a &nbsp; d &nbsp; i &nbsp; z &nbsp; : &nbsp; s &nbsp; t &nbsp; o &nbsp; r &nbsp; y</a>
                </li>
               </ul>
            </div>
          </article>
        <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li class="on">
                  <a href="#"><span>펀딩 후기</span></a>
                </li>
                <li>
                  <a href="#"><span>기부 후기</span></a>
                </li>
                <li>
                  <a href="#"><span>프로젝트 후기</span><span class="badge"></span></a>
                </li>
              </ul>
            </div>
          </nav>
          
		 <article class="gridContent2">
            <div class="rowInner">
              <div class="titleBar">
                <div class="title"><h2>펀딩 후기</h2></div>
       		  </div>
       		
       		<form name="storyForm" method="post">
    		<div class="write">
                 <div class="write2">
                         <span class="aaa">펀딩 후기 작성하기 </span>
                        <div class="btnbox1">
                         <button type="button" class="imgbtn" onclick=""> [사진올리기]</button>
                        </div> 
                 </div>
                 <div class="writebox">
                       <textarea name="content" id="content" class="boxTF" rows="3" required="required"></textarea>
                  </div>
                  <div class="btnbox2">
                       <button type="button" class="btn" onclick="sendStory()"> 등록하기 </button>
                  </div>           
            </div>
            </form>
         
 			<ul class="gridContent2">
                <li class="item2" >
                  <div class="itemInner2" align="center">
     
                    <div
                      class="thumbnail2"
                      style="
                        background-image: url('https://media.istockphoto.com/photos/close-up-interviewer-interview-candidate-apply-for-job-at-meeting-in-picture-id1159531985?b=1&k=6&m=1159531985&s=170667a&w=0&h=owBebdTBKEqWlmpebcyfTh9TZVtMFmW6Id7wuykuVsg=');
                      "
                    >
                      <span class="hidden">내용</span>
                    </div>
                    <div class="textWrap2">
                      <div class="subject2">
                        <a href="#"><span class="subject_s">기부 후기</span></a>
                      </div>
                      <div class="www">
                      	<span>${sessionScope.member.memberId}</span>
                      </div>
                      <div class="bbox">
                      	<span class="bbb">
                      		${dto.storyContent}
						</span>
                      </div>
                      
                    <div class="likebox">
					 <button type="button" class="like">
						<span class="likecount"> ♥ 1</span>
					 </button>
				    </div>
                      
                      <div align="right">
                      <ul class="desc">
                        <li><span> ${sessionScope.member.memberId} </span></li>
                        <li><span> ${dto.storyCreated} </span></li>
                        <li><span> ♥ 1</span></li>
                        <li><span></span>
                      </ul>
                      </div>
                    </div>
                  </div>
                </li>
	          </ul>
	       
	      
       		</div>
          
      
 		<table style="width: 100%; border-spacing: 0px;">
			<tr height="35">
				<td align="center">
					${dataCount==0 ? "등록된 게시물이 없습니다.":paging}
				</td>
			</tr>
		</table>
</article>