<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
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
              <ul class="categoryContent2">
                <li style="background-image: url('https://cdn.pixabay.com/photo/2020/06/07/13/21/toronto-5270413_960_720.jpg');">
                  <a> e &nbsp; y &nbsp; d &nbsp; i &nbsp; z &nbsp; : &nbsp; c &nbsp; a &nbsp; s &nbsp; t &nbsp;</a>
                </li>
               </ul>
            </div>
          </article>
          
          <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li>
                  <a href="<%=cp%>/cast/news?castCnum=1"><span>이디즈 넥스트</span></a>
                </li>
                <li class="on">
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
                  <form name="searchForm" action="<%=cp%>/cast/news?castCnum=2" method="post" >
                    <input
                      type="text"
                      name="keyword"
                      class="keyword transparent"
                      placeholder="검색"
                      value="${keyword}"
                    />
                    <button type="button" class="searchButton" onclick="searchList()"><i class="fa fa-search" aria-hidden="true"></i></button>
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
				<li class="title"><a href="${readUrl}&castNum=${dto.castNum}&castCnum=${dto.castCnum}"> ${dto.castTitle} </a></li>
				<li class="name">${dto.memberId}</li>
				<li class="ncontentn">${dto.castSubtitle}</li>
				<li> <img class="thumbnail" style="background-image: url('${dto.castThumnail}')"></li>
			 </ul>
			 <ul class="desc">
                <li><span> ${dto.memberId} </span></li>
                <li><span> ${dto.castCreated} </span></li>
                <li><span> ♥&nbsp;${dto.castLikeCount}</span></li>
             </ul>
		    </div>
            </c:forEach>
            <c:if test="${sessionScope.member.memberId=='eydiz'}">
		    <div class="btnbox2">
               <input type="hidden" name="castCnum" value="${castCnum}">
               <button type="submit" class="btn" onclick="javascript:location.href='<%=cp%>/cast/write2?castCnum=2';"> 등록하기 </button>
            </div>
            </c:if>   
           </div>
 		<table style="width: 100%; border-spacing: 0px;">
			<tr height="35">
				<td align="center">
					 ${dataCount==0?"등록된 게시물이 없습니다.":paging}
				</td>
			</tr>
		</table>
</article>