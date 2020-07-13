<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<script type="text/javascript">
function searchlist() {
	var f = document.sendsearchForm;
	f.submit();
}

function ajaxJSON(url, method, query, fn) {
	$.ajax({
		type:method,
		url:url,
		data:query,
		dataType:"json",
		success:function(data) {
			fn(data);
		},
		beforeSend:function(jqXHR) {
			jqXHR.setRequestHeader("AJAX", true);
		},
		error:function(jqXHR) {
			if(jqXHR.state==403) {
				login();
				return false;
			}
			
			console.log(jqXHR.responseText);
		}
	});
}

$(function() {
	$(".send_button_r").click(function() {
		if(! confirm("배송 상태를 변경 하시겠습니까 ?? ")) {
			return false;			
		}
		
		var url = "<%=cp%>/studio/project/updateState";
		var buyNo = $(this).attr("data-buyNo");
		var query = "buyNo="+buyNo+"&projectNo=${projectNo}&page=${page}";
		alert(query);
		location.href = url+"?"+query;
	});
});

</script>

 <div class="studio_send_form">
           <div class="titleBar">
             <div class="title"><h2>배송 준비 중</h2></div>
			     <form name="sendsearchForm" action="" method="post" >
               		<div align="right" style="margin-bottom: 10px;">
	                    <select name="condition">
	                      <option value="buyNo" ${condition=="buyNo" ? "selected='selected'":""}>판매 번호</option>
	                    </select>
	                    <input type="text" name="keyword"placeholder="검색" value="${keyword}"/>
	                    <input type="hidden" name="rows" value="${rows}">
	                    <button type="button" class="v_searchButton" onclick="searchlist()"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>
				 </form>
              </div>
            <div class="send_view" >
             <table class="send_view_info">
             	<tr class="send_view_o">
             		<td class="send_no">판매 번호</td>
					<td class="send_mNo">멤버 번호</td> 
					<td class="send_rsubject">구입 날짜</td> 
					<td class="send_sendStatus">배송상태</td> 
					<td class="send_button"> 배송 상태 변경 </td>
				</tr>
				<c:forEach var="dto" items="${readylist}">
	             	<tr class="send_view_t">
	             		<td class="send_no">${dto.buyNo}</td>
						<td class="send_mNo">${dto.memberNo}</td> 
						<td class="send_rsubject"><a href="<%=cp%>/studio/project/shipping/article/${projectNo}?buyNo=${dto.buyNo}&page=${page}">${dto.boughtDate}</a></td> 
						<td class="send_sendStatus">${dto.statusName}</td> 
						<td> <a class="send_button_r" id="sendN" type="button" data-buyNo="${dto.buyNo}">배송 중</a> </td>
					</tr>
				</c:forEach>
			 </table>
		    </div>

           </div>
	 		<table style="width: 1100px; border-spacing: 0px;">
				<tr height="35">
					<td align="center">
						 ${readyCount==0?"데이터가 없습니다.":paging}
					</td>
				</tr>
			</table>