<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">

function searchList() {
	var f=document.searchForm;
	f.submit();
}

$(function(){
	var castCnum="${castCnum}";
	$("#castItemMenu"+castCnum).addClass("on");
	
});

</script>

<article class="row">
	 <div class="rowInner">
              <ul class="categoryncontent">
                <li style="background-image: url('https://cdn.pixabay.com/photo/2020/06/07/13/21/toronto-5270413_960_720.jpg');">
                  <a> w &nbsp; a &nbsp; d &nbsp; i &nbsp; z &nbsp; : &nbsp; c &nbsp; a &nbsp; s &nbsp; t &nbsp;</a>
                </li>
               </ul>
            </div>
          </article>
          
          <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li id="storyItemMenu1">
                  <a href="<%=cp%>/cast/news?castCnum=1"><span>이디즈 넥스트</span></a>
                </li>
                <li id="storyItemMenu2">
                  <a href="<%=cp%>/cast/news?castCnum=2"><span>이디즈 뉴스</span></a>
                </li>
              </ul>
            </div>
          </nav>
          
          <article class="row">
            <div class="rowInner">
              <div class="titleBar">
                <div class="title"><h2>이디즈 뉴스</h2></div>
                <div class="option">
                  <form name="searchForm" action="<%=cp%>/cast/news" method="post">
                    <input
                      type="text"
                      name="keyword"
                      class="keyword transparent"
                      placeholder="검색"
                      value="${keyword}"
                    />
                    <a href="#" class="searchButton" onclick="searchList()"><span class="hidden">검색</span></a>
                    <select name="condition">
                      <option value="castTitle" ${condition=="castTitle"?"selected='selected'":""}>제목</option>
                      <option value="castContent" ${condition=="castContent"?"selected='selected'":""}>내용</option>
                      <option value="castCreated" ${condition=="castCreated"?"selected='selected'":""}>작성일자</option>
                    </select>
                  </form>
                </div>
              </div>
              
            <c:forEach var="dto" items="${list}">
            <div class="row2" >
             <ul>
				<li class="title"><a href="${readUrl}&castNum=${dto.castNum}"> ${dto.castTitle} </a></li>
				<li class="name">${sessionScope.member.memberId}</li>
				<li class="ncontent">${dto.castContent}</li>
				<li> <img class="thumbnail" style="background-image: url('https://cdn.pixabay.com/photo/2020/06/03/16/30/wave-5255593__340.jpg')"></li>
			 </ul>
			 <ul class="desc">
                <li><span> ${sessionScope.member.memberId} </span></li>
                <li><span> ${dto.castCreated} </span></li>
                <li><span> ♥ </span></li>
             </ul>
		    </div>
            </c:forEach>
		    <div class="btnbox2">
               <input type="hidden" name="castCnum" value="${castCnum}">
               <button type="submit" class="btn" onclick="javascript:location.href='<%=cp%>/cast/write2?castCnum=2';"> 등록하기 </button>
            </div>   
           </div>
      
 		<table style="width: 100%; border-spacing: 0px;">
			<tr height="35">
				<td align="center">
					 ${dataCount==0?"등록된 게시물이 없습니다.":paging}
				</td>
			</tr>
		</table>
</article>