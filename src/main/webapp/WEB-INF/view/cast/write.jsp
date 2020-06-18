<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<div class="inner">
          <div class="cast">
           <h3><span class="cast_s"> 캐스트 </span> </h3>
          </div>
      
           <table class="writebox">
           <tr class="boxrow1" align="left"> 
               <td class="title">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
               <td class="title2"> 
                 <input class="text_in" type="text" name="title">
               </td>
           </tr>
         
           <tr class="boxrow2" align="left"> 
               <td class="writer">작성자</td>
               <td class="writer2">관리자</td>
           </tr>
         
           <tr class="boxrow3" align="left"> 
               <td class="text">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
               <td class="text2"> 
                 <textarea class="aaa" name="wcontent" rows="12" style="width: 95%;"></textarea>
               </td>
           </tr>
           </table>
         
           <table class="btnbox">
              <tr height="45"> 
               <td align="center" >
                 <button type="button" class="btn">등록하기</button>
                 <button type="reset" class="btn">다시입력</button>
                 <button type="button" class="btn">취소</button>
               </td>
             </tr>
           </table>
          </div>