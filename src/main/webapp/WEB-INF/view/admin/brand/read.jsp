<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<div class="admin_brand_infotitle">
	<div class="titleBar">
       <div class="title"><h2>브랜드 정보</h2></div>
    </div>

<div class="admin_brand_listinfo" >
    <table class="admin_list_brandinfo">
       <tr>
       	<td class="admin_list_aaa"> ❏    회원이 등록한 브랜드 정보를 확인하세요.</td>
       </tr>
       <tr>
         <td class="admin_list_out">브랜드 이름</td>
	   </tr>
	    <tr>
         <td class="admin_list_in">${dto.brandName}</td>
	   </tr>
	    <tr>
         <td class="admin_list_out">관리자 이름</td>
	   </tr>
	    <tr>
         <td class="admin_list_in">${dto.managerName}</td>
	   </tr>
	    <tr>
         <td class="admin_list_out">관리자 이메일</td>
	   </tr>
	    <tr>
         <td class="admin_list_in">${dto.managerEmail}</td>
	   </tr>
	    <tr>
         <td class="admin_list_out">관리자 전화번호</td>
	   </tr>
	    <tr>
         <td class="admin_list_in">${dto.managerPhone}</td>
	   </tr>
	</table>
 </div>
</div> 