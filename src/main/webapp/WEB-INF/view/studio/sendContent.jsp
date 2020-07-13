<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<div class="smanagetitle">
	<h2>배송 정보 상세 내역</h2>
</div>
<div>
	<p>판매 번호</p>
	<input type="text" value="${dto.buyNo}" readonly="readonly">
</div>
<div>
	<p>배송 상태</p>
	<input type="text" value="${dto.statusName}" readonly="readonly">
</div>
<div>
	<p>수령인</p>
	<input type="text" value="${dto.recipient}" readonly="readonly">
</div>
<div>
	<p>메시지</p>
	<input type="text" value="${dto.message}" readonly="readonly">
</div>
<div>
	<p>배송 시작일</p>
	<input type="text" value="${dto.startShippingDate}" readonly="readonly">
</div>
<div>
	<p>주소</p>
	<input id="zip" type="text" value="${dto.zipCode}" readonly="readonly">
</div>
<div>
	<input id="add1" type="text" value="${dto.address1}" readonly="readonly">
	<input id="add2" type="text" value="${dto.address2}" readonly="readonly">
</div>

