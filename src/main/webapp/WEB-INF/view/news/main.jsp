<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<article class="row">
  		<div class="contentWrapper">
        	<div class="mainBoard">
        		<div class="boardHeader">
        			<div class="boardTitle">
        				<h2>공지사항</h2>
        			</div>
        			<div>
        				<ul class="boardmenu">
        					<li class="menuon"> <a href="#"><span>전체</span></a> </li>
        					<li class="menuon"> <a href="#"><span>공지</span></a> </li>
        					<li class="menuon"> <a href="#"><span>이벤트</span></a> </li>
        					<li class="menuon"> <a href="#"><span>보도자료</span></a> </li>
        				</ul>
        			</div>
        		</div>
        		<div class="borderBody">
        			<div>
						<ul>
							<li>공지·알림</li>
						</ul>
						<ul>
							<li><a href="#">와디즈 심사 정책에 대한 안내</a></li>
						</ul>
						<ul>
							<li>와디즈&nbsp;2020.05.05</li>
						</ul>   
        			</div>
        		</div>
        		<div class="boardfooter">
        			<ul>
						<li> < 1 2 3 4 > </li>
					</ul> 
        		</div>
        		<div class="boardsearch">
        			<ul>
        				<li>
        					<select name="">
        						<option value="">제목</option>
        						<option value="">내용</option>
        						<option value="">작성자</option>
        					</select>
        				<li>
        				<li> <input type="text"> </li>
        				<li> <button>검색</button> </li>
        			</ul>
        		</div>
        	</div>
        </div>
</article>