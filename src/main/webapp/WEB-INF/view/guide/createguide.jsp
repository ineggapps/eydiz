<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<article class="row">
	<div class="guidecreate">
        	<ul>
        		<li> <p>제목 : </p> </li>
        		<li> <input type="text"> </li>
        	</ul>
        	<ul>
        		<li> <p>내용 : </p> </li>
        		<li> <textarea></textarea> </li>
        	</ul>
        	<ul>
        		<li> <p>파일 : </p> </li>
        		<li> <input type="file"> </li>
        	</ul>
        	<br><br><br>
        	<ul>
        		<li>
        			<button>등록하기</button>
        			<button>다시입력</button>
        			<button>등록취소</button>
        		</li>
        	</ul>
        </div>
</article>