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
              <ul class="categoryContent1">
                <li style="background-image: url('https://cdn.pixabay.com/photo/2020/06/07/13/21/toronto-5270413_960_720.jpg');">
                  <a> e &nbsp; y &nbsp; d &nbsp; i &nbsp; z &nbsp; : &nbsp; c &nbsp; a &nbsp; s &nbsp; t &nbsp;</a>
                </li>
               </ul>
            </div>
          </article>
          
           <nav class="row">
            <div class="rowFull">
              <ul class="detailNav">
                <li class="on">
                  <a href="<%=cp%>/cast/news?castCnum=1"><span>이디즈 넥스트</span></a>
                </li>
                <li>
                  <a href="<%=cp%>/cast/news?castCnum=2"><span>이디즈 뉴스</span></a>
                </li>
              </ul>
            </div>
          </nav>
          
          <article class="row">
            <div class="rowInner">
              <div class="titleBar">
                <div class="title"><h2>이디즈 넥스트</h2></div>
                <div class="option">
                   <form name="searchForm" action="<%=cp%>/cast/news?castCnum=1" method="post">
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
              
              <ul class="gridContent1">
              <c:forEach var="dto" items="${list}">
                <li class="item1">
                  <div class="itemInner1">
                    <div
                      class="thumbnail1"
                      style="
                        background-image: url('${dto.castThumnail}');
                      "
                    >
                      <span class="hidden">호호</span>
                    </div>
                    <div class="textWrap1">
                      <div class="subject1">
                        <a  href="${readUrl}&castNum=${dto.castNum}&castCnum=${dto.castCnum}"
                          ><span
                            >&lt; 와디즈 넥스트 &gt; ${dto.castTitle}</span
                          ></a
                        >
                      </div>
                      <ul class="desc1">
                        <li><span class="category"> ${dto.memberId} </span></li>
                        <li><span class="name">  ${dto.castCreated} </span></li>
                        <li><span class="name"> ♥ </span></li>
                      </ul>
                    </div>
                  </div>
                </li>
              </c:forEach>
              </ul>
              
             <div class="btnbox2">
               <input type="hidden" name="castCnum" value="${castCnum}">
               <button type="submit" class="btn" onclick="javascript:location.href='<%=cp%>/cast/write1?castCnum=1';"> 등록하기 </button>
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