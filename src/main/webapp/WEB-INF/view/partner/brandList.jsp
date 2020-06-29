<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
   String cp=request.getContextPath();
%>
				
			<form name="partnerSearchForm" method="post">
		          <div class = "partnerSearchBox">
		          	<input type="text" name="keyword" id = "partnerSearchKeyword" class= "partnerSearch" value="${keyword}" placeholder=" 브랜드이름을 입력하세요">
					<button type="button" class = "partnerSearchBtn" onclick="searchList();"></button>
					<button type="button" class = "partnerReloadBtn" onclick="reloadList();"></button>
		          </div>
	        </form>
			<ul class="partnerGridContent partnerListContent">
                <c:forEach var="dto" items="${list}">
	                <li class="item partnerBox" onclick="location.href='${articleUrl}&brandNo=${dto.brandNo}';">
	                  <div class="itemInner partnerBBoard">
	                    <div class="thumbnail"style="background-image: url('${dto.memberImageUrl}');">
	                      <span class="hidden">드럼</span>
	                    </div>
	                    <div class="textWrap ">
	                      <div class="subject awardsTabBName">
	                        <span>${dto.brandName}</span>
	                      </div>
	                      <div class = "partnerBDetail">
	                      	<span>대표 &nbsp;&nbsp;&nbsp;</span><span class="partnerBData">${dto.managerName}</span><br>
	                      	<span>이메일 &nbsp;&nbsp;</span><span class="partnerBData"> ${dto.managerEmail}</span><br>
	                      </div>
	                    </div>
	                  </div>
	                </li>
                </c:forEach>
              </ul>
              <div class = "partnerPaging">
              	${dataCount==0?"등록된 브랜드가 존재하지 않습니다.":paging}
              </div>