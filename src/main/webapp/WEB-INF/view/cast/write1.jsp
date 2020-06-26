<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
    function send() {
        var f = document.castForm;

    	var str = f.castTitle.value;
        if(!str) {
            alert("제목을 입력하세요. ");
            f.castTitle.focus();
            return ;
        }

    	str = f.castContent.value;
    	  if(!str || str=="<p>&nbsp;</p>") {
            alert("내용을 입력하세요. ");
            f.castContent.focus();
            return ;
        }

    	f.action="<%=cp%>/cast/${mode}";

       f.submit();
    }
</script>
	<div class="wcontent">
	 <form name="castForm" method="post">
		<div class="inner">
         <div class="cast">
          <h3><span class="cast_s"> 캐스트 </span> </h3>
         </div>
      	  
           <table class="writebox">
           <tr class="boxrow1" align="left"> 
               <td class="title">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
               <td class="title2"> 
                 <input class="text_in" type="text" name="castTitle" value="${dto.castTitle}">
               </td>
           </tr>
         
           <tr class="boxrow2" align="left"> 
               <td class="writer">작성자</td>
               <td class="writer2">${sessionScope.member.memberId}</td>
           </tr>
         
           <tr class="boxrow3" align="left"> 
               <td class="text">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
               <td class="text2"> 
                 <textarea class="aaa" name="castContent" rows="12" style="width: 95%;">${dto.castContent}</textarea>
               </td>
           </tr>
           </table>
          </div>
           
           <table class="btnbox">
              <tr height="45"> 
               <td align="center" >
                  <input type="hidden" name="castCnum" value="${castCnum}">
                 <button type="button" class="btn" onclick="send()">${mode == 'update'?'수정하기':'등록하기'}</button>
                 <button type="reset" class="btn">다시입력</button>
                 <button type="button" class="btn" onclick="javascript:location.href='<%=cp%>/cast/news';">${mode == 'update'?'수정취소':'등록취소'}</button>
              	 <c:if test="${mode == 'update'}">
              	 	<input type="hidden" name="castNum" value="${dto.castNum}">
              	 	<input type="hidden" name="page" value="${page}">
              	 </c:if>
               </td>
             </tr>
           </table>
           
           
          </form>
         </div>